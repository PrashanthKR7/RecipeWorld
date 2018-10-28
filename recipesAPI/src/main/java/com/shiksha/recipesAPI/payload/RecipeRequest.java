package com.shiksha.recipesAPI.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private int category;
	@NotBlank
	@Size(min = 200)
	private String steps;
	@NotNull
	private int cookingTime;

	@Override
	public String toString() {
		return "RecipeRequest [title=" + title + ", author=" + author + ", description=" + description + ", category="
				+ category + ", steps=" + steps + ", cookingTime=" + cookingTime + "]";
	}

	public RecipeRequest(String title, String author, String description, int category, String steps, int cookingTime) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.category = category;
		this.steps = steps;
		this.cookingTime = cookingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + category;
		result = prime * result + cookingTime;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeRequest other = (RecipeRequest) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category != other.category)
			return false;
		if (cookingTime != other.cookingTime)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
