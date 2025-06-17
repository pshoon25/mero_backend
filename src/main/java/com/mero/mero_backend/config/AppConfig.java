package com.mero.mero_backend.config;

import com.mero.mero_backend.converter.Encrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Encrypt encrypt() {
        return new Encrypt();
    }
}