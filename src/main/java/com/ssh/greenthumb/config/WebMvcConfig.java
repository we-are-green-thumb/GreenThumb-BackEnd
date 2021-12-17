package com.ssh.greenthumb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "cors")
public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Value("${cors.allowed-origins}")
//    private String allowedOrigins;
//
//    @Value("${cors.allowed-credentials}")
//    private boolean allowedCredentials;
//
//    @Value("${cors.allowed-methods}")
//    private List<String> allowedMethods = new ArrayList<>();
//
//    @Value("${cors.allowed-headers")
//    private String allowedHeaders;
//
//    @Value("${cors.max-age}")
//    private final Long maxAge = 3600L;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("Authorization")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8081");
    }

}