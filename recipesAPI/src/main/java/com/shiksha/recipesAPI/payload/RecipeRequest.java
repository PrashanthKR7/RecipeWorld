package com.shiksha.recipesAPI.payload;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shiksha.recipesAPI.model.Instruction;
import com.shiksha.recipesAPI.model.dto.Ingredient;

import lombok.Data;

@Data
public class RecipeRequest implements Serializable {

	private static final long serialVersionUID = -1661496498457237508L;

	private Long id;
	@NotBlank
	@Size(min = 4, max = 30)
	private String title;
	@NotBlank
	@Size(min = 4, max = 40)
	private String author;
	@NotBlank
	@Size(min = 40, max = 500)
	private String description;
	@NotNull
	private List<Instruction> instructions;
	@NotNull
	private int cookingTime;
	@NotNull
	private int preparingTime;
	@NotNull
	private String thumbnail;
	@NotNull
	private Long cookingstyle;
	@NotNull
	private Long diettype;
	@NotNull
	private Long cuisine;
	@NotNull
	private Long mealtype;
	@NotNull
	private Long difficulty;
	private String[] tags;
	@NotNull
	private boolean isVeg;
	@NotNull
	private List<Ingredient> ingredients;
	private String link;
	private List<Ingredient> keyIngredients;
}
