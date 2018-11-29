package com.shiksha.recipesAPI.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiksha.recipeAPI.dao.RecipeDAO;
import com.shiksha.recipesAPI.exception.ResourceNotFoundException;
import com.shiksha.recipesAPI.model.CookingStyle;
import com.shiksha.recipesAPI.model.Cuisine;
import com.shiksha.recipesAPI.model.DietType;
import com.shiksha.recipesAPI.model.Difficulty;
import com.shiksha.recipesAPI.model.Instruction;
import com.shiksha.recipesAPI.model.KeyIngredient;
import com.shiksha.recipesAPI.model.MealType;
import com.shiksha.recipesAPI.model.Recipe;
import com.shiksha.recipesAPI.model.dto.Ingredient;
import com.shiksha.recipesAPI.payload.RecipeRequest;
import com.shiksha.recipesAPI.payload.Response;
import com.shiksha.recipesAPI.service.DefaultCookingStyleService;
import com.shiksha.recipesAPI.service.DefaultCuisineService;
import com.shiksha.recipesAPI.service.DefaultDietTypeService;
import com.shiksha.recipesAPI.service.DefaultDifficultyService;
import com.shiksha.recipesAPI.service.DefaultIngredientService;
import com.shiksha.recipesAPI.service.DefaultMealTypeService;
import com.shiksha.recipesAPI.validator.ExistingAuthorValidator;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

	final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	private RecipeDAO recipeService;
	@Autowired
	ExistingAuthorValidator validator;
	@Autowired
	private DefaultDifficultyService difficultyService;
	@Autowired
	private DefaultMealTypeService mealTypeService;
	@Autowired
	private DefaultDietTypeService dietTypeService;
	@Autowired
	private DefaultCuisineService cuisineService;
	@Autowired
	private DefaultCookingStyleService cookingStyleService;
	@Autowired
	private DefaultIngredientService ingredientService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, path = "/meta")
	public ResponseEntity<Response<List<String>>> recipeMeta(HttpServletRequest request) {
		List<String> meta = (List<String>) recipeService.getRecipeMetafieldsData();
		Response<List<String>> response = new Response<>(HttpStatus.OK.value(), null,
				messageSource.getMessage("recipe.meta", new String[] {}, Locale.ROOT), request.getRequestURI(), meta,
				HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response<List<String>>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response<Recipe>> addRecipe(@Valid @RequestBody RecipeRequest recipeRequest,
			BindingResult result, HttpServletRequest request) {

//		User user = userService.findByUsername(recipeRequest.getAuthor()).orElseThrow(() -> {
//			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
//					new String[] { "user", "username", recipeRequest.getAuthor() }, Locale.ROOT));
//		});

		Difficulty difficulty = difficultyService.getById(recipeRequest.getDifficulty()).orElse(null);
		Cuisine cuisine = cuisineService.getById(recipeRequest.getCuisine()).orElse(null);
		DietType dietType = dietTypeService.getById(recipeRequest.getDiettype()).orElse(null);
		MealType mealType = mealTypeService.getById(recipeRequest.getMealtype()).orElse(null);
		CookingStyle cookingStyle = cookingStyleService.getById(recipeRequest.getCookingstyle()).orElse(null);

		if (!recipeRequest.getKeyIngredients().isEmpty()) {

			for (Ingredient keyIngr : recipeRequest.getKeyIngredients()) {
				KeyIngredient ingredient = new KeyIngredient(((Ingredient) keyIngr).getName());
				try {
					ingredientService.save(ingredient);
				} catch (DataIntegrityViolationException e) {
					LOGGER.warn("Unique constraint violation in adding ingredients");
				}
			}
//			List<KeyIngredient> ingredients = recipeRequest.getKeyIngredients().stream()
//					.map(ingredient -> new KeyIngredient(((Ingredient) ingredient).getName()))
//					.collect(Collectors.toList());
//			ingredientService.saveAll(ingredients);
		}

		Set<Instruction> instructions = new HashSet<Instruction>(recipeRequest.getInstructions());

		Recipe recipe = new Recipe(recipeRequest.getTitle(), recipeRequest.getThumbnail(), recipeRequest.getAuthor(), recipeRequest.getDescription(),
				recipeRequest.getPreparingTime(), difficulty, cuisine, mealType, dietType, cookingStyle,
				recipeRequest.getLink(), recipeRequest.getTags(), recipeRequest.isVeg(), null,
				recipeRequest.getCookingTime(), recipeRequest.getIngredients());

		for (Instruction instruction : instructions) {
			instruction.setRecipe(recipe);
		}

		recipe.setInstructions(instructions);
		// Check validation errors in service
		recipe = recipeService.createRecipe(recipe);
		LOGGER.debug("Added:: " + recipe);

		Response<Recipe> response = new Response<>(HttpStatus.CREATED.value(), null,
				messageSource.getMessage("recipe.created", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.CREATED.getReasonPhrase());
		return new ResponseEntity<Response<Recipe>>(response, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response<Recipe>> updateRecipe(@Valid @RequestBody RecipeRequest recipeRequest,
			HttpServletRequest request) {

		Recipe recipe = recipeService.getById(recipeRequest.getId()).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeRequest.getId() + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "title", recipeRequest.getTitle() }, Locale.ROOT));
		});

//		User user = userService.findByUsername(recipeRequest.getAuthor()).orElseThrow(() -> {
//			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
//					new String[] { "user", "username", recipeRequest.getAuthor() }, Locale.ROOT));
//		});

		// recipe.setAuthor(user);

		recipeService.save(recipe);

		Response<Recipe> response = new Response<>(HttpStatus.OK.value(), null,
				messageSource.getMessage("recipe.updated", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response<Recipe>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Response<Recipe>> getRecipe(@PathVariable("id") Long recipeId, HttpServletRequest request) {
		Recipe recipe = recipeService.getById(recipeId).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeId + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "id", recipeId.toString() }, Locale.ROOT));
		});

		LOGGER.debug("Found Employee:: " + recipe);

		Response<Recipe> response = new Response<>(HttpStatus.FOUND.value(), null,
				messageSource.getMessage("recipe.found", new String[] { recipe.getTitle() }, Locale.ROOT),
				request.getRequestURI(), recipe, HttpStatus.FOUND.getReasonPhrase());
		return new ResponseEntity<Response<Recipe>>(response, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response<List<Recipe>>> getAllRecipes(HttpServletRequest request) {
		List<Recipe> recipes = recipeService.getAll();
		if (recipes.isEmpty()) {
			LOGGER.debug("Recipes does not exists.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipes", "", "" }, Locale.ROOT));

		}
		LOGGER.debug(recipes);
		LOGGER.debug("Found " + recipes.size() + " Recipes");
		LOGGER.debug(Arrays.toString(recipes.toArray()));
		Response<List<Recipe>> response = new Response<>(HttpStatus.FOUND.value(), null,
				messageSource.getMessage("recipe.found", null, Locale.ROOT), request.getRequestURI(), recipes,
				HttpStatus.FOUND.getReasonPhrase());
		return new ResponseEntity<Response<List<Recipe>>>(response, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Response<Recipe>> deleteEmployee(@PathVariable("id") Long recipeId,
			HttpServletRequest request) {
		Recipe recipe = recipeService.getById(recipeId).orElseThrow(() -> {
			LOGGER.debug("Recipe with id " + recipeId + " does not exist.");
			throw new ResourceNotFoundException(messageSource.getMessage("app.resource_not_found",
					new String[] { "recipe", "id", recipeId.toString() }, Locale.ROOT));
		});

		recipeService.delete(recipeId);
		LOGGER.debug("Recipe with id " + recipeId + "deleted");

		Response<Recipe> response = new Response<>(HttpStatus.GONE.value(), null,
				messageSource.getMessage("recipe.deleted",  new String[] { recipe.getTitle() }, Locale.ROOT), request.getRequestURI(), recipe,
				HttpStatus.GONE.getReasonPhrase());
		return new ResponseEntity<Response<Recipe>>(response, HttpStatus.FOUND);
	}

}
