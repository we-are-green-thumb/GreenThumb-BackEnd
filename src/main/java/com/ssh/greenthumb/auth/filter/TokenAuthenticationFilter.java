package com.ssh.greenthumb.auth.filter;

import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.domain.RefreshToken;
import com.ssh.greenthumb.auth.repository.RefreshTokenRepository;
import com.ssh.greenthumb.auth.service.CustomUserDetailsService;
import com.ssh.greenthumb.auth.token.Token;
import com.ssh.greenthumb.auth.token.TokenProvider;
import com.ssh.greenthumb.auth.token.UsernamePasswordAuthenticationToken;
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

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private RefreshTokenRepository refreshTokenDao;
    @Autowired
    private UserRepository userDao;
    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getJwtFromRequest(request);
        if (accessToken != null) {
            try {
                Long userId = tokenProvider.getUserIdFromToken(accessToken);
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                User user = userDao.findById(userId).get();
                RefreshToken refreshToken = refreshTokenDao.findByUser(user);

                // 엑세스 토큰이 유효하면
                if (tokenProvider.validateToken2(accessToken)) {
                    System.out.println("==========111" + userId);
//                    Long userId = tokenProvider.getUserIdFromToken(accessToken);
//                    UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else if (!tokenProvider.validateToken2(accessToken) && (refreshToken != null)) {
                    System.out.println("=====만료토큰 아이디 가져오기 가능?=====2222");
//                    if (!tokenProvider.validateToken2(refreshToken.getRefreshToken())) {
//                        refreshTokenDao.deleteByUser(user);
//                        throw new BadRequestException("재로그인 필요");
//                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, user.getPassword());

//                    Authentication authentication = authenticationManager.authenticate()
//                    Authentication authentication = authenticationManager.authenticate(aa);
                    Token token = tokenProvider.reissue(userId, refreshToken.getRefreshToken());
                    response.setHeader("authorization", "bearer " + token.getAccessToken());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("=====재발급 됐니???=====" + token.getAccessToken());
                }

            } catch (Exception ex) {
                log.error("Security Context에서 사용자 인증을 설정할 수 없습니다", ex);
            }
        }

        filterChain.doFilter(request, response);


//                String jwt = getJwtFromRequest(request);
////
////            System.out.println(authentication);
//
////            RefreshToken refreshToken = refreshTokenDao.findByUser(user);
////            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
////            ResponseEntity res = tokenProvider.reissue(userId, refreshToken.getRefreshToken(), authentication);
////            System.out.println(res.getBody().toString());
//                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//                    Long userId = tokenProvider.getUserIdFromToken(jwt);
//                    UserDetails userDetails = customUserDetailsService.loadUserById(userId);
////                User user = userDao.findById(userId).
////                        orElseThrow(NotFoundException::new);
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
////            } else if (!tokenProvider.validateToken(jwt) && refreshTokenDao.findByUser(user) != null) {
////                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                SecurityContextHolder.getContext().setAuthentication(authentication);
////                response.setHeader("Authorization", );
//                }
//            }
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
