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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiksha.recipesAPI.exception.ResourceNotFoundException;
import com.shiksha.recipesAPI.model.Recipe;
import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.payload.RecipeRequest;
import com.shiksha.recipesAPI.payload.Response;
import com.shiksha.recipesAPI.service.RecipeService;
import com.shiksha.recipesAPI.service.UserService;
import com.shiksha.recipesAPI.validator.ExistingAuthorValidator;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	private RecipeService recipeService;
	@Autowired
	ExistingAuthorValidator validator;
	@Autowired
	private UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addRecipe(@Valid @RequestBody RecipeRequest recipeRequest, BindingResult result,
			HttpServletRequest request) {

		User user = userService.findByUsername(recipeRequest.getAuthor()).orElseThrow(() -> {
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "username", recipeRequest.getAuthor() }, Locale.ROOT));
		});

		Recipe recipe = new Recipe(recipeRequest.getTitle(), user, recipeRequest.getDescription(),
				recipeRequest.getSteps(), recipeRequest.getCookingTime());

		// Check validation errors
		recipeService.save(recipe);
		LOGGER.debug("Added:: " + recipe);

		Response response = new Response(HttpStatus.CREATED.value(), null,
				messageSource.getMessage("recipe.created", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.CREATED.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response> updateRecipe(@Valid @RequestBody RecipeRequest recipeRequest,
			HttpServletRequest request) {

		Recipe recipe = recipeService.getById(recipeRequest.getId()).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeRequest.getId() + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "title", recipeRequest.getTitle() }, Locale.ROOT));
		});

		User user = userService.findByUsername(recipeRequest.getAuthor()).orElseThrow(() -> {
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "user", "username", recipeRequest.getAuthor() }, Locale.ROOT));
		});

		recipe.setAuthor(user);

		recipeService.save(recipe);

		Response response = new Response(HttpStatus.OK.value(), null,
				messageSource.getMessage("recipe.updated", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Response> getRecipe(@PathVariable("id") Long recipeId, HttpServletRequest request) {
		Recipe recipe = recipeService.getById(recipeId).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeId + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "id", recipeId.toString() }, Locale.ROOT));
		});

		LOGGER.debug("Found Employee:: " + recipe);

		Response response = new Response(HttpStatus.FOUND.value(), null,
				messageSource.getMessage("recipe.found", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.FOUND.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response> getAllRecipes(HttpServletRequest request) {
		List<Recipe> recipes = recipeService.getAll();
		if (recipes.isEmpty()) {
			LOGGER.debug("Recipes does not exists.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipes", "", "" }, Locale.ROOT));

		}
		LOGGER.debug(recipes);
		LOGGER.debug("Found " + recipes.size() + " Recipes");
		LOGGER.debug(Arrays.toString(recipes.toArray()));
		Response response = new Response(HttpStatus.FOUND.value(), null,
				messageSource.getMessage("recipe.found", null, Locale.ROOT), request.getRequestURI(), recipes,
				HttpStatus.FOUND.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable("id") Long recipeId, HttpServletRequest request) {
		Recipe recipe = recipeService.getById(recipeId).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeId + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "id", recipeId.toString() }, Locale.ROOT));
		});

		recipeService.delete(recipeId);
		LOGGER.debug("Recipe with id " + recipeId + "deleted");

		Response response = new Response(HttpStatus.GONE.value(), null,
				messageSource.getMessage("recipe.deleted", null, Locale.ROOT), request.getRequestURI(), recipe,
				HttpStatus.GONE.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}

}
