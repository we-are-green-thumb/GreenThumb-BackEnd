package com.ssh.greenthumb;

import com.ssh.greenthumb.config.auth.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class GreenthumbApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenthumbApplication.class, args);
    }

}