package com.gymmgtsystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gymmgtsystem.listener.AuditEventListerner;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfig {
    
    @Bean
	public AuditorAware<String> auditorAware() {
		return new AuditEventListerner();
	}
}