package com.dcankayrak.springSecurityDemo;

import com.dcankayrak.springSecurityDemo.config.JwtService;
import com.dcankayrak.springSecurityDemo.entities.User;
import com.dcankayrak.springSecurityDemo.entities.enums.Role;
import com.dcankayrak.springSecurityDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityDemoApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			JwtService authenticationService
	){
		return args -> {
			var admin = User
					.builder()
					.email("test12345")
					.password(passwordEncoder.encode("test"))
					.role(Role.ADMIN)
					.build();
			System.out.println("ADMIN TOKEN : "+authenticationService.generateToken(admin));

			this.userRepository.save(admin);

			var manager = User
					.builder()
					.email("test123456")
					.password(passwordEncoder.encode("test"))
					.role(Role.MANAGER)
					.build();
			System.out.println("MANAGER TOKEN : "+authenticationService.generateToken(manager));

			this.userRepository.save(manager);
		};
	}
}
