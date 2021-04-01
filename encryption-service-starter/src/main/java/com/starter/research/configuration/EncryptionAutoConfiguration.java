package com.starter.research.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.encryption.EncryptionService;
import com.starter.research.config.EncryptionConfig;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(EncryptionService.class)
@EnableConfigurationProperties(EncryptionConfig.class)
public class EncryptionAutoConfiguration {

    private final EncryptionConfig encryptionConfig;

    @Bean
    public EncryptionService encryptionService() {
        return new EncryptionService();
    }



}
