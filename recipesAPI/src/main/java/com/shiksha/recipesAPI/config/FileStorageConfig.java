package com.shiksha.recipesAPI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
	private String uploadDir;
	
}
