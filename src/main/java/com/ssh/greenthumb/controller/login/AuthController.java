package com.ssh.greenthumb.controller.login;

import com.ssh.greenthumb.common.exception.BadRequestException;
import com.ssh.greenthumb.dao.user.UserRepository;
import com.ssh.greenthumb.domain.user.User;
import com.ssh.greenthumb.dto.login.ApiResponse;
import com.ssh.greenthumb.dto.login.AuthResponse;
import com.ssh.greenthumb.dto.login.LoginRequest;
import com.ssh.greenthumb.dto.login.SignUpRequest;
import com.ssh.greenthumb.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

//@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        System.out.println(1);
        if(userDao.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 해당 이메일을 사용하고 있습니다.");
        }

        System.out.println(2);
        User result = userDao.save(User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                .provider()
                .build()
        );

        System.out.println(3);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getEmail()).toUri();

        System.out.println(4);
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "계정 생성 성공"));
    }

}