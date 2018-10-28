package com.shiksha.recipesAPI.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipe")
@EntityListeners(AuditingEntityListener.class)
public class Recipe implements Serializable {

	private static final long serialVersionUID = -1661496498457237508L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recipeId", unique = true, nullable = false)
	private Long recipeId;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "author", referencedColumnName = "username", nullable = false)
	@JsonIgnore
	private User author;

	@Column(name = "description", length = 500, nullable = false)
	@Size(max = 500)
	private String description;

	@Column(name = "created_on", nullable = false, updatable = false)
	@CreatedDate
	private Date createdOn;

	@Column(name = "updated_on", nullable = false)
	@LastModifiedDate
	private Date lastUpdatedOn;

	private Category category;

	@Column(name = "steps", nullable = false)
	@Lob
	@Size(min = 200)
	private String steps;

	@Column(name = "cooking_time", nullable = false)
	private int cookingTime;

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Recipe(String title, User author, String description, Date createdOn, Date lastUpdatedOn) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdOn = createdOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Recipe(String title, User author, String description, String steps, int cookingTime) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.steps = steps;
		this.cookingTime = cookingTime;
	}

	public Recipe() {
	}

	@Override
	public int hashCode() {
		return recipeId == null ? 0 : recipeId.hashCode();
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", title=" + title + ", author=" + author + ", description="
				+ description + ", createdOn=" + createdOn + ", updatedOn=" + lastUpdatedOn + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (recipeId == null) {
			if (other.recipeId != null)
				return false;
		} else if (!recipeId.equals(other.recipeId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
			return false;
		return true;
	}

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "category")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

}
