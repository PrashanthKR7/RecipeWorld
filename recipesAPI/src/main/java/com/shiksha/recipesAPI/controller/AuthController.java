package com.shiksha.recipesAPI.controller;

import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
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

import com.shiksha.recipeAPI.dao.RoleDAO;
import com.shiksha.recipeAPI.dao.UserDAO;
import com.shiksha.recipesAPI.exception.AppException;
import com.shiksha.recipesAPI.exception.BadRequestException;
import com.shiksha.recipesAPI.model.Role;
import com.shiksha.recipesAPI.model.RoleName;
import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.payload.JwtAuthenticationResponse;
import com.shiksha.recipesAPI.payload.LoginRequest;
import com.shiksha.recipesAPI.payload.Response;
import com.shiksha.recipesAPI.payload.SignUpRequest;
import com.shiksha.recipesAPI.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private MessageSource messageSource;
	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDAO userService;

	@Autowired
	RoleDAO roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(method = RequestMethod.POST, value = "/signin")
	public ResponseEntity<Response<JwtAuthenticationResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
			HttpServletRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		Response<JwtAuthenticationResponse> response = new Response<>(HttpStatus.OK.value(), null,
				messageSource.getMessage("user.login", new String[] {}, Locale.ROOT), request.getRequestURI(),
				new JwtAuthenticationResponse(jwt), HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response<JwtAuthenticationResponse>>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public ResponseEntity<Response<JwtAuthenticationResponse>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
			HttpServletRequest request) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			throw new BadRequestException(messageSource.getMessage("user.name_already_used",
					new String[] { signUpRequest.getUsername() }, Locale.ROOT));
		}

		if (userService.existsByEmailId(signUpRequest.getEmail())) {
			throw new BadRequestException(messageSource.getMessage("user.email_already_used",
					new String[] { signUpRequest.getEmail() }, Locale.ROOT));
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), "", signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleService.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException(messageSource.getMessage("user.role_not_set", null, Locale.ROOT)));

		user.setRoles(Collections.singleton(userRole));
		User result = userService.save(user);
		
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		Response<JwtAuthenticationResponse> response = new Response<>(HttpStatus.OK.value(), null,
				messageSource.getMessage("user.registration", new String[] { result.getUsername() }, Locale.ROOT),
				request.getRequestURI(), new JwtAuthenticationResponse(jwt), HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response<JwtAuthenticationResponse>>(response, HttpStatus.OK);

		// URI location =
		// ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
		// .buildAndExpand(result.getUsername()).toUri();
		// return ResponseEntity.created(location)
		// .body(new ApiResponse(true,
		// messageSource.getMessage("user.registration_success", null, Locale.ROOT)));
	}
}
