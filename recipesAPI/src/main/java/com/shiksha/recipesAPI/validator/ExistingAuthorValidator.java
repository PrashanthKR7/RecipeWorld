package com.shiksha.recipesAPI.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.shiksha.recipeAPI.dao.UserDAO;
import com.shiksha.recipesAPI.model.User;

@Component
public class ExistingAuthorValidator implements Validator {

	@Autowired
	private UserDAO userService;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		String  username = (String) o;
		
		if (username == null || !userService.findByUsernameOrEmailId(username, username).isPresent()) {
			errors.reject(HttpStatus.NOT_FOUND.value() + "", messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "username", username }, Locale.ROOT));
		}
	}

}
