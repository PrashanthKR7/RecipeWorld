package com.shiksha.recipesAPI.payload;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String username;

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public JwtAuthenticationResponse(String accessToken, String username) {
		this.accessToken = accessToken;
		this.username = username;
	}
	
	
}