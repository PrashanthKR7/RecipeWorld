package com.shiksha.recipesAPI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppConfig {
	private String jwtSecret;
	private String jwtExpirationInMs;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public String getJwtExpirationInMs() {
		return jwtExpirationInMs;
	}

	public void setJwtExpirationInMs(String jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

}
