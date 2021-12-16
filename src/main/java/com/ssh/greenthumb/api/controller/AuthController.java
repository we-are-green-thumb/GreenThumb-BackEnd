package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.common.exception.BadRequestException;
import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.login.ApiResponse;
import com.ssh.greenthumb.api.dto.login.AuthResponse;
import com.ssh.greenthumb.api.dto.login.LoginRequest;
import com.ssh.greenthumb.api.dto.login.SignUpRequest;
import com.ssh.greenthumb.auth.domain.*;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import com.ssh.greenthumb.auth.token.AppProperties;
import com.ssh.greenthumb.auth.token.Token;
import com.ssh.greenthumb.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenDao;
    @Autowired
    private AppProperties appProperties;

    @Transactional
    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest) {
        if (userDao.findByEmailAndIsDeleted(loginRequest.getEmail(), "n") == null) {
            throw new NotFoundException();
        } else {
            User user = userDao.findByEmailAndIsDeleted(loginRequest.getEmail(), "n");

            if (user.getRole() == Role.BLACK || user.getRole() == Role.DELETE) {
                throw new BadRequestException("접근 권한이 없습니다.");
            } else if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new BadRequestException("비밀번호가 틀립니다.");
            } else if (refreshTokenDao.findByUser(user) != null) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

                return tokenProvider.reissue(user.getId(), refreshTokenDao.findByUser(user).getRefreshToken(), authentication);
            } else {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                Token token = tokenProvider.createToken(authentication);

                refreshTokenDao.save(RefreshToken.builder()
                        .user(user)
                        .refreshToken(token.getRefreshToken())
                        .build());

                return new ResponseEntity(AuthResponse.builder()
                        .accessToken(token.getAccessToken())
                        .id(user.getId())
                        .build(), HttpStatus.OK);
            }
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if(userDao.existsByEmail(signUpRequest.getEmail())) {
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
                .body(new ApiResponse(true, "계정 생성 성공"));
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userDao.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

//    @GetMapping("/valid")
//    public boolean valid(@RequestHeader("Authorization") String token) {
//        try {
//            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            System.out.println(("유효하지 않은 JWT 서명"));
//        } catch (MalformedJwtException ex) {
//            System.out.println(("유효하지 않은 JWT 토큰"));
//        } catch (ExpiredJwtException ex) {
//            System.out.println("만료된 JWT 토큰");
//        } catch (UnsupportedJwtException ex) {
//            System.out.println("지원하지 않는 JWT 토큰");
//        } catch (IllegalArgumentException ex) {
//            System.out.println("비어있는 JWT");
//        }
//        return false;
//    }

}