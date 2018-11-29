package com.shiksha.recipesAPI.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiksha.recipeAPI.dao.UserDAO;
import com.shiksha.recipesAPI.exception.ResourceNotFoundException;
import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.payload.Response;
import com.shiksha.recipesAPI.payload.UserIdentityAvailability;
import com.shiksha.recipesAPI.payload.UserProfile;
import com.shiksha.recipesAPI.payload.UserSummary;
import com.shiksha.recipesAPI.security.CurrentUser;
import com.shiksha.recipesAPI.security.UserPrincipal;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	private UserDAO userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response<User>> addUser(@Valid @RequestBody User user, HttpServletRequest request) {
		userService.save(user);
		LOGGER.debug("Added:: " + user);
		Response<User> response = new Response<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				messageSource.getMessage("user.created", new String[] { user.getUsername() }, Locale.ROOT),
				request.getRequestURI(), user);
		return new ResponseEntity<Response<User>>(response, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response<User>> updateUser(@Valid @RequestBody User user, HttpServletRequest request) {
		userService.getById(user.getId()).orElseThrow(() -> {
			LOGGER.debug("User with id " + user.getId() + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "username", user.getUsername() }, Locale.ROOT));
		});

		userService.save(user);

		Response<User> response = new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				messageSource.getMessage("user.updated", new String[] { user.getUsername() }, Locale.ROOT),
				request.getRequestURI(), null);
		return new ResponseEntity<Response<User>>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response<List<User>>> getAllUsers(HttpServletRequest request) {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			LOGGER.debug("Users does not exists.");
			throw new ResourceNotFoundException(
					messageSource.getMessage("user.not_exists", new String[] { "s" }, Locale.ROOT));

		}
		LOGGER.debug(users);
		LOGGER.debug("Found " + users.size() + " Users");
		LOGGER.debug(Arrays.toString(users.toArray()));

		Response<List<User>> response = new Response<>(HttpStatus.FOUND.value(), HttpStatus.FOUND.getReasonPhrase(),
				messageSource.getMessage("user.found", new String[] { "s" }, Locale.ROOT), request.getRequestURI(),
				users);
		return new ResponseEntity<Response<List<User>>>(response, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Response<Object>> deleteEmployee(@PathVariable("id") Long userId, HttpServletRequest request) {
		userService.getById(userId).orElseThrow(() -> {
			LOGGER.debug("User with id " + userId + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "id", userId.toString() }, Locale.ROOT));
		});

		userService.delete(userId);
		LOGGER.debug("User with id " + userId + "deleted");
		Response<Object> response = new Response<>(HttpStatus.GONE.value(), HttpStatus.GONE.getReasonPhrase(),
				messageSource.getMessage("user.deleted", null, Locale.ROOT), request.getRequestURI(), null);
		return new ResponseEntity<Response<Object>>(response, HttpStatus.GONE);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Object>> getCurrentUser(@CurrentUser UserPrincipal currentUser, HttpServletRequest request) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());

		Response<Object> response = new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				messageSource.getMessage("user.deleted", null, Locale.ROOT), request.getRequestURI(), userSummary);
		return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/checkUsernameAvailability")
	public ResponseEntity<Response<UserIdentityAvailability>> checkUsernameAvailability(@RequestParam(value = "username") String username,
			HttpServletRequest request) {
		Boolean isAvailable = !userService.existsByUsername(username);

		HttpStatus httpStatus = isAvailable ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		Response<UserIdentityAvailability> response = new Response<>(httpStatus.value(), httpStatus.getReasonPhrase(),
				messageSource.getMessage("user.name_already_used", new String[] { username }, Locale.ROOT),
				request.getRequestURI(), new UserIdentityAvailability(isAvailable));
		return new ResponseEntity<Response<UserIdentityAvailability>>(response, httpStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/checkEmailAvailability")
	public ResponseEntity<Response<UserIdentityAvailability>> checkEmailAvailability(@RequestParam(value = "emailId") String emailId,
			HttpServletRequest request) {
		Boolean isAvailable = !userService.existsByEmailId(emailId);

		HttpStatus httpStatus = isAvailable ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		Response<UserIdentityAvailability> response = new Response<>(httpStatus.value(), httpStatus.getReasonPhrase(),
				messageSource.getMessage("user.email_already_used", new String[] { emailId }, Locale.ROOT),
				request.getRequestURI(), new UserIdentityAvailability(isAvailable));
		return new ResponseEntity<Response<UserIdentityAvailability>>(response, httpStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{username}")
	public ResponseEntity<Response<UserProfile>> getUserProfile(@PathVariable(value = "username") String username,
			HttpServletRequest request) {
		User user = userService.findByUsername(username).orElseThrow(() -> {
			LOGGER.debug("User " + username + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "username", username }, Locale.ROOT));
		});

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getFirstName(),
				user.getCreatedAt());

		Response<UserProfile> response = new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				messageSource.getMessage("user.found", new String[] { username }, Locale.ROOT), request.getRequestURI(),
				userProfile);
		return new ResponseEntity<Response<UserProfile>>(response, HttpStatus.OK);
	}

}
