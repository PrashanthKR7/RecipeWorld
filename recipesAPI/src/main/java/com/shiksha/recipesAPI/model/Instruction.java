package com.shiksha.recipesAPI.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instruction")
public class Instruction implements Serializable {

	private static final long serialVersionUID = 5337959078207191140L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String content;
	@Column
	private String image;
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name = "recipe", nullable = false)
	private Recipe recipe;

	public Instruction(String content, String image, Recipe recipe) {
		super();
		this.content = content;
		this.image = image;
		this.recipe = recipe;
	}
}
