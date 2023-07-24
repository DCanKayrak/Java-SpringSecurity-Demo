package com.dcankayrak.springSecurityDemo.service;

import com.dcankayrak.springSecurityDemo.config.JwtService;
import com.dcankayrak.springSecurityDemo.dto.AuthResponse;
import com.dcankayrak.springSecurityDemo.dto.UserRegisterRequestDto;
import com.dcankayrak.springSecurityDemo.entities.User;
import com.dcankayrak.springSecurityDemo.entities.enums.Role;
import com.dcankayrak.springSecurityDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }

    public void register(UserRegisterRequestDto request) {
        User newUser = User
                .builder()
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(newUser);
    }

    public AuthResponse login(UserRegisterRequestDto request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());

        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        var user= userRepository.findByEmail(request.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Kullanıcıya ait email bulunamadı."));

        var jwt=jwtService.generateToken(user);
        AuthResponse result = AuthResponse.builder().token(jwt).build();
        return result;
    }
}
