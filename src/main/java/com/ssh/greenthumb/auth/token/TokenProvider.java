package com.ssh.greenthumb.auth.token;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.domain.RefreshToken;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final AppProperties appProperties;

    @Autowired
    private RefreshTokenRepository refreshTokenDao;
    @Autowired
    private UserRepository userDao;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Transactional
    public Token createToken(Long userId) {
        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + appProperties.getAuth().getAccessTokenExpiry()))
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + appProperties.getAuth().getRefreshTokenExpiry()))
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();

        User user = userDao.findById(userId).orElseThrow(NotFoundException::new);

        if (refreshTokenDao.findByUser(user) != null) {
            refreshTokenDao.deleteByUser(user);
        }
        refreshTokenDao.save(RefreshToken.builder().user(user).refreshToken(refreshToken).build());

        return Token.builder()
                 .accessToken(accessToken)
                 .refreshToken(refreshToken)
                 .build();
    }

    @Transactional
    public Token reissue(Long userId, String refreshToken) {
        if (validateToken(refreshToken)) {
            String accessToken = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(String.valueOf(userId))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + appProperties.getAuth().getAccessTokenExpiry()))
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();

            return Token.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            }
        return createToken(userId);
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
            Jws<Claims> claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return claims.getBody().getExpiration().before(new Date(System.currentTimeMillis()));
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
