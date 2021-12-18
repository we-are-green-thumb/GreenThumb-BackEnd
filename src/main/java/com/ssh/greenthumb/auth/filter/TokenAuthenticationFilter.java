package com.ssh.greenthumb.auth.filter;

import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import com.ssh.greenthumb.auth.service.CustomUserDetailsService;
import com.ssh.greenthumb.auth.token.AppProperties;
import com.ssh.greenthumb.auth.token.Token;
import com.ssh.greenthumb.auth.token.TokenProvider;
import com.ssh.greenthumb.auth.token.UsernamePasswordAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private RefreshTokenRepository refreshTokenDao;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private AppProperties appProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getJwtFromRequest(request);

        if (accessToken != null) {
            try {
                Long userId = tokenProvider.getUserIdFromToken(accessToken);
                if (tokenProvider.validateToken(accessToken)) {
                    UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else if (!tokenProvider.validateToken(accessToken)) {
                    User user = userDao.findById(userId).get();

                    String refreshToken = refreshTokenDao.findByUser(user).getRefreshToken();

                    if (!tokenProvider.validateToken(refreshToken)) {
                        Claims claim = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret())
                                .parseClaimsJws(refreshTokenDao.findByUser(user).getRefreshToken())
                                .getBody();
                        claim.setExpiration(new Date(new Date().getTime() + appProperties.getAuth().getRefreshTokenExpiry()));

                        Token token = tokenProvider.reissue(userId, refreshToken);

                        UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        response.setHeader("Authorization", token.getAccessToken());
                    } else if (tokenProvider.validateToken(refreshToken)) {
                        Token token = tokenProvider.reissue(userId, refreshToken);

                        UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        response.setHeader("Authorization", token.getAccessToken());
                    }
                }

            } catch (Exception ex) {
                log.error("Security Context에서 사용자 인증을 설정할 수 없습니다", ex);
            }
        }
        filterChain.doFilter(request, response);
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
