package com.guoanfamily.palmsale.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "guoanfamily.security.urlConfig")
public class TokenConfigUrlSettings {

    private String loginUrl;
    private String tokenRefresh;
    private String checkUrl;
    private String[] nonCheckUrl;
    private String headerParam;
}
