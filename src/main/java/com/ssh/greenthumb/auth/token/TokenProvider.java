package com.ssh.greenthumb.auth.token;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.domain.RefreshToken;
import com.ssh.greenthumb.auth.domain.UserPrincipal;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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
    public Token createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + appProperties.getAuth().getAccessTokenExpiry()))
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpiry()))
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();

        User user = userDao.findById(userPrincipal.getId()).orElseThrow(NotFoundException::new);

        if (refreshTokenDao.findByUser(user) != null) {
            refreshTokenDao.deleteByUser(user);
        }
//        } else if (refreshTokenDao.findByUser(user) != null && validateToken(refreshTokenDao.findByUser(user).getRefreshToken())) {
//            Claims claim = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret())
//                    .parseClaimsJws(refreshTokenDao.findByUser(user).getRefreshToken())
//                    .getBody();
//            claim.setExpiration(new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpiry()));
//        }
        refreshTokenDao.save(RefreshToken.builder().user(user).refreshToken(refreshToken).build());

        return Token.builder()
                 .accessToken(accessToken)
                 .refreshToken(refreshToken)
                 .build();
    }

    @Transactional
    public Token reissue(Long userId, String refreshToken) {
        if (validateToken(refreshToken)) {
            Date now = new Date();

            String accessToken = Jwts.builder()
                    .setSubject(String.valueOf(userId))
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + appProperties.getAuth().getAccessTokenExpiry()))
                    .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                    .compact();

            User user = userDao.findById(userId).orElseThrow(NotFoundException::new);

            refreshTokenDao.deleteByUser(user);

            return Token.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            }
        User user = userDao.findById(userId).orElseThrow(NotFoundException::new);

        Authentication authentication = getAuthentication(refreshToken);

        return createToken(authentication);
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
//            return true;
            return !claims.getBody().getExpiration().before(new Date());
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

    public boolean validateToken2(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            log.info(e.getMessage());
            return false;
        }
    }

}
