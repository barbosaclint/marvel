package com.clint.marvel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("com.marvel.ms.url")
public class UrlProperties {

    private String base;

}
