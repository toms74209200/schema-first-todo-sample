package io.github.toms74209200.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("server")
public record ServerConfig(String domain) {}
