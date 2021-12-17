package com.ssh.greenthumb.auth.token;

import com.ssh.greenthumb.api.common.exception.BadRequestException;
import com.ssh.greenthumb.api.dto.login.AuthResponse;
import com.ssh.greenthumb.auth.domain.UserPrincipal;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final AppProperties appProperties;
    @Autowired
    private RefreshTokenRepository refreshTokenDao;
//    @Autowired
//    private UserRepository userDao;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Token createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + appProperties.getAuth().getAccessTokenExpiry()))
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpiry()))
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();

        System.out.println(appProperties.getAuth().getAccessTokenExpiry());
        System.out.println(now.getTime());

        return Token.builder()
                 .accessToken(accessToken)
                 .refreshToken(refreshToken)
                 .build();
    }

    public ResponseEntity reissue(Long userId, String refreshToken, Authentication authentication) {
        if(validateToken(refreshToken)) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Date now = new Date();

            String accessToken = Jwts.builder()
                    .setSubject(Long.toString(userPrincipal.getId()))
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + appProperties.getAuth().getAccessTokenExpiry()))
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();

            return new ResponseEntity(AuthResponse.builder()
                    .accessToken(accessToken)
                    .userId(userId)
                    .build(), HttpStatus.OK);
        }else {
            throw new BadRequestException("토큰 리프레시 불가");
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("유효하지 않은 JWT 서명");
        } catch (MalformedJwtException ex) {
            log.error("유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException ex) {
            log.error("만료된 JWT 토큰");
        } catch (UnsupportedJwtException ex) {
            log.error("지원하지 않는 JWT 토큰");
        } catch (IllegalArgumentException ex) {
            log.error("비어있는 JWT");
        }
        return false;
    }

}
