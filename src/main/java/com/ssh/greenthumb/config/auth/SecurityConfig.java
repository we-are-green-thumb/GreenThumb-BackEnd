package com.ssh.greenthumb.config.auth;

import com.ssh.greenthumb.security.CustomUserDetailsService;
import com.ssh.greenthumb.security.TokenAuthenticationFilter;
import com.ssh.greenthumb.security.oauth2.CustomOAuth2UserService;
import com.ssh.greenthumb.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ssh.greenthumb.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.ssh.greenthumb.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
      HttpCookieOAuth2AuthorizationReqeustRepository
      - JWT를 사용하기 때문에 Session에 저장할 필요가 없어져, Authorization Request를 Based64 encoded cookie에 저장
    */

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    // Authorization에 사용할 userDetailService와 password Encoder 정의
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // SecurityConfig에서 사용할 password encoder를 BCryptPasswordEncoder로 정의
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //
//    /*
//      AuthenticationManager를 외부에서 사용하기 위해 AuthenticationManager Bean을 통해
//      @Autowired가 아닌 @Bean 설정으로 Spring Security 밖으로 Authentication 추출
//     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); // 기본값이 on인 csrf 취약점 보안을 해제한다. on으로 설정해도 되나 설정할경우 웹페이지에서 추가처리가 필요함.
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//        http.headers().
//                frameOptions().sameOrigin() // SockJS는 기본적으로 HTML iframe 요소를 통한 전송을 허용하지 않도록 설정되는데 해당 내용을 해제한다.
//                .and()
//                .formLogin() // 권한없이 페이지 접근하면 로그인 페이지로 이동한다.
//                .and()
//                .authorizeRequests()
//                .antMatchers("/templates/**").hasRole("USER") // chat으로 시작하는 리소스에 대한 접근 권한 설정
////                .antMatchers("/user/**").authenticated()   // /user로 들어오는 모든 요청은 권한 필요
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")   // /admin으로 들어오는 모든 요청은 ADMIN이라는 역할이 있는 사람만 접근 가능 (스프링 시큐리티에서는 항상 ROLE_ADMIN 이런 형태여야 한다)
//                .anyRequest().authenticated().and().formLogin().and().httpBasic();  // 그외 요청은 모두 접근 가능


        http

////                // CORS 허용
                .cors()
                .and()
////                // 토큰을 사용하기 위해 sessionCreationPolicy를 STATELESS로 설정 (Session 비활성화)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
////                // CSRF 비활성화
                .csrf().disable()
////                // 로그인폼 비활성화
                .formLogin().disable()
//                .formLogin()
//                .loginPage("localhost:8081/login")
//                .loginProcessingUrl("/user/login").and()
//                .and()
////                // 기본 로그인 창 비활성화
                .httpBasic().disable()
                .oauth2Login()
                .defaultSuccessUrl("/index.html")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
//                .authorizeRequests()
//                .antMatchers("/", "/test").permitAll();
//                .antMatchers("/**").hasAnyRole(Role.BLACK.name() ,Role.USER.name(), Role.ADMIN.name());

//                .web.ignoring().antMatchers("/assets/**")
//                .web.ignoring().antMatchers("/favicon.ico");
//                .antMatchers("/auth/**", "/oauth2/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                // 클라이언트 처음 로그인 시도 URI
//                .baseUri("/oauth2/authorization")

//                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//                .and()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler);
//////
////        // Add our custom Token based authentication filter
////        // UsernamePasswordAuthenticationFilter 앞에 custom 필터 추가!
////        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
////        http.csrf().disable()
//
//                // exception handling 할 때 우리가 만든 클래스를 추가
////                .exceptionHandling()
////                .and()
////                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                // 시큐리티는 기본적으로 세션을 사용
//                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
////                .and()
////                .authorizeRequests()
////                .antMatchers("/auth/**").permitAll()
////                .anyRequest().authenticated();   // 나머지 API 는 전부 인증 필요
//
//                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
//
//
////                .logout()
////                .logoutSuccessUrl("/index.html")
////                .and()
////                .oauth2Login()
////                .defaultSuccessUrl("/index.html")
////                .userInfoEndpoint()
////                .userService(customOAuth2UserService);
//
////                .csrf().disable()
////                .headers().frameOptions().disable()
////                .and()
////                // authorizeRequests
////                // URL별 권한 관리를 설정하는 옵션의 시작점이다. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.
////                .authorizeRequests()
////                // antMatchers
////                // 권한 관리 대상을 지정하는 옵션이다.
////                // URL, HTTP 메소드별로 관리가 가능하다. 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 관한을 준다.
////                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console?**").permitAll()
////                // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 한다.
////                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
////                // anyRequest
////                // 설정된 값 이외의 나머지 URL들을 나타낸다. authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들(로그인한 사용자들)에게만 허용한다.
////                .anyRequest().authenticated()
////                .and()
////                // .logout().logoutSuccessUrl("/")
////                // 로그아웃 기능에 대한 여러 설정의 진입점이다. 로그아웃 성공시 "/" 주소로 이동한다.
////                .logout()
////                .logoutSuccessUrl("/")
////                .and()
////                // oauth2Login
////                // OAuth 2 로그인 기능에 대한 여러 설정의 진입점이다.
////                .oauth2Login()
////                // userInfoEndpoint
////                // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당한다.
////                .userInfoEndpoint()
////                // userService
////                // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
////                // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시할 수 있다.
////                .userService(customOAuth2UserService);

//        http
//                .cors()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf()
//                .disable()
//                .formLogin()
//                .disable()
//                .httpBasic()
//                .disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/",
//                        "/error",
//                        "/favicon.ico",
//                        "/**/*.png",
//                        "/**/*.gif",
//                        "/**/*.svg",
//                        "/**/*.jpg",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js")
//                .permitAll()
//                .antMatchers("/auth/**", "/oauth2/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorize")
//                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//                .and()
//                .redirectionEndpoint()
//                .baseUri("/oauth2/callback/*")
//                .and()
//                .userInfoEndpoint()
////                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler);
//
//        // Add our custom Token based authentication filter
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}