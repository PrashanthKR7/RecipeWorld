package com.shiksha.recipesAPI.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shiksha.recipesAPI.exception.AppException;
import com.shiksha.recipesAPI.exception.BadRequestException;
import com.shiksha.recipesAPI.model.Role;
import com.shiksha.recipesAPI.model.RoleName;
import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.payload.ApiResponse;
import com.shiksha.recipesAPI.payload.JwtAuthenticationResponse;
import com.shiksha.recipesAPI.payload.LoginRequest;
import com.shiksha.recipesAPI.payload.SignUpRequest;
import com.shiksha.recipesAPI.security.JwtTokenProvider;
import com.shiksha.recipesAPI.service.RoleService;
import com.shiksha.recipesAPI.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private MessageSource messageSource;
	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			throw new BadRequestException(messageSource.getMessage("user.name_already_used", null, Locale.ROOT));
		}

		if (userService.existsByEmailId(signUpRequest.getEmailId())) {
			throw new BadRequestException(messageSource.getMessage("user.email_already_used", null, Locale.ROOT));
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), "", signUpRequest.getUsername(), signUpRequest.getEmailId(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleService.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException(messageSource.getMessage("user.role_not_set", null, Locale.ROOT)));

		user.setRoles(Collections.singleton(userRole));

		User result = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location)
				.body(new ApiResponse(true, messageSource.getMessage("user.registration_success", null, Locale.ROOT)));
	}
}
