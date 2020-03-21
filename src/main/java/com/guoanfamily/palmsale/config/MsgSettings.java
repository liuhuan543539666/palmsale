package com.guoanfamily.palmsale.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "guoanfamily.msg")
public class MsgSettings {
    private String username = "xzgadc";
    private String password = "Gadc836121";
    private String epid = "121638";
}
