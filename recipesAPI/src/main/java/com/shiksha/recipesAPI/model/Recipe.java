package com.shiksha.recipesAPI.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shiksha.recipesAPI.model.dto.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor @ToString
@Entity
@Table(name = "recipe")
@EntityListeners(AuditingEntityListener.class)
@NamedStoredProcedureQueries({ @NamedStoredProcedureQuery(name = "getRecipeMeta", procedureName = "recipe_meta") })
public class Recipe extends BaseModel implements Serializable {

	private static final long serialVersionUID = -1661496498457237508L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long recipeId;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "thumbnail", nullable = false)
	private String thumbnail;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "description", length = 500, nullable = false)
	@Size(max = 500)
	private String description;

	@Column(name = "created_on", nullable = false, updatable = false)
	@CreatedDate
	private Date createdOn;

	@Column(name = "updated_on", nullable = false)
	@LastModifiedDate
	private Date lastUpdatedOn;

	@Column(name = "prep_Time")
	private int preparingTime;

	@ManyToOne(optional = false)
	@JoinColumn(name = "difficulty", referencedColumnName = "id", nullable = false)
	private Difficulty difficulty;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cuisine", referencedColumnName = "id", nullable = false)
	private Cuisine cuisine;

	@ManyToOne(optional = false)
	@JoinColumn(name = "mealtype", referencedColumnName = "id", nullable = false)
	private MealType mealtype;

	@ManyToOne(optional = false)
	@JoinColumn(name = "diettype", referencedColumnName = "id", nullable = false)
	private DietType diettype;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cookingstyle", referencedColumnName = "id", nullable = false)
	private CookingStyle cookingstyle;

	@Column(name = "link")
	private String link;

	@Type(type = "json")
	@Column(columnDefinition = "json", name = "tags")
	private String[] tags;

	@Column(name = "veg", nullable = false)
	private Boolean isVeg;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Instruction> instructions = new HashSet<>();

	@Column(name = "cooking_time", nullable = false)
	private int cookingTime;

	@Type(type = "json")
	@Column(columnDefinition = "json", name = "ingredients", nullable = false)
	private List<Ingredient> ingredients;

	public Recipe(String title, String thumbnail, String author, @Size(max = 500) String description, int preparingTime,
			Difficulty difficulty, Cuisine cuisine, MealType mealtype, DietType diettype, CookingStyle cookingstyle,
			String link, String[] tags, Boolean isVeg, Set<Instruction> instructions, int cookingTime,
			List<Ingredient> ingredients) {
		this.title = title;
		this.thumbnail = thumbnail;
		this.author = author;
		this.description = description;

		this.preparingTime = preparingTime;
		this.difficulty = difficulty;
		this.cuisine = cuisine;
		this.mealtype = mealtype;
		this.diettype = diettype;
		this.cookingstyle = cookingstyle;
		this.link = link;
		this.tags = tags;
		this.isVeg = isVeg;
		this.instructions = instructions;
		this.cookingTime = cookingTime;
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (cookingTime != other.cookingTime)
			return false;
		if (cookingstyle == null) {
			if (other.cookingstyle != null)
				return false;
		} else if (!cookingstyle.equals(other.cookingstyle))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (cuisine == null) {
			if (other.cuisine != null)
				return false;
		} else if (!cuisine.equals(other.cuisine))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (diettype == null) {
			if (other.diettype != null)
				return false;
		} else if (!diettype.equals(other.diettype))
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (isVeg == null) {
			if (other.isVeg != null)
				return false;
		} else if (!isVeg.equals(other.isVeg))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (mealtype == null) {
			if (other.mealtype != null)
				return false;
		} else if (!mealtype.equals(other.mealtype))
			return false;
		if (preparingTime != other.preparingTime)
			return false;
		if (recipeId == null) {
			if (other.recipeId != null)
				return false;
		} else if (!recipeId.equals(other.recipeId))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((recipeId == null) ? 0 : recipeId.hashCode());
		return result;
	}

}
