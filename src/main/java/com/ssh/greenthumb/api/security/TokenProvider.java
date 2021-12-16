package com.ssh.greenthumb.api.security;

import com.ssh.greenthumb.config.auth.AppProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;              // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;
//
//    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        // 시간 개긴거 같은데?
        System.out.println(expiryDate.getTime());

        // 중요한 정보를 refresh토큰에만 담는 듯듯
       return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public void createRefreshToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        // 일반 토큰이랑 시간을 달리 설정해주어야할 듯 싶음
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        String refreshToken = Jwts.builder()
                        .setSubject(Long.toString(userPrincipal.getId()))
                        .setIssuedAt(new Date())
                        .setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                        .compact();

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
            logger.error("유효하지 않은 JWT 서명");
        } catch (MalformedJwtException ex) {
            logger.error("유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException ex) {
            logger.error("만료된 JWT 토큰");
        } catch (UnsupportedJwtException ex) {
            logger.error("지원하지 않는 JWT 토큰");
        } catch (IllegalArgumentException ex) {
            logger.error("비어있는 JWT");
        }
        return false;
    }

}
