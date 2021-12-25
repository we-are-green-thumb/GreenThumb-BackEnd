package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.BadRequestException;
import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.login.AuthRequest;
import com.ssh.greenthumb.api.dto.login.AuthResponse;
import com.ssh.greenthumb.api.dto.login.BasicResponse;
import com.ssh.greenthumb.api.dto.login.SignUpRequest;
import com.ssh.greenthumb.auth.domain.AuthProvider;
import com.ssh.greenthumb.auth.domain.Role;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import com.ssh.greenthumb.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenDao;

    public Object authenticateUser(AuthRequest.Login loginRequest) {
        User user = userDao.findByEmailAndIsDeleted(loginRequest.getEmail(), "n");

        if (user == null) {
            throw new NotFoundException();
        } else if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("비밀번호가 틀립니다.");
        } else if (user.getRole() == Role.BLACK || user.getRole() == Role.DELETE) {
            throw new BadRequestException("접근 권한이 없습니다.");
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.createToken(authentication);

            return new ResponseEntity(AuthResponse.builder()
                    .accessToken(accessToken)
                    .userId(user.getId())
                    .build(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 해당 이메일을 사용하고 있습니다.");
        }

        User result = userDao.save(User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .nickName(signUpRequest.getNickName())
                .provider(AuthProvider.local)
                .role(Role.USER)
                .build()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location)
                .body(new BasicResponse(true, "계정 생성 성공"));
    }

    public void logout(Long id) {
        User user = userDao.findById(id).get();

        refreshTokenDao.deleteByUser(user);
    }

//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
//        return userDao.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//    }

}
