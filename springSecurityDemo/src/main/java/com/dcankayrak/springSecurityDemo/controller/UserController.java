package com.dcankayrak.springSecurityDemo.controller;

import com.dcankayrak.springSecurityDemo.dto.AuthResponse;
import com.dcankayrak.springSecurityDemo.dto.UserRegisterRequestDto;
import com.dcankayrak.springSecurityDemo.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRegisterRequestDto request){

        return new ResponseEntity<>(userService.login(request),HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequestDto request){
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
