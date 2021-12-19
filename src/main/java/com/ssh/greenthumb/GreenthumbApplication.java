package com.ssh.greenthumb;

import com.ssh.greenthumb.auth.token.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAutoConfiguration
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class GreenthumbApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GreenthumbApplication.class, args);
    }

}