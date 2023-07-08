package com.workspace.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="twilio")
@Getter
@Setter
public class SmsConfig {
	private String accountSid;
	private String authToken;
	private String trialNumber;
	@Bean
    public String myString() {
        return new String();
    }
}
