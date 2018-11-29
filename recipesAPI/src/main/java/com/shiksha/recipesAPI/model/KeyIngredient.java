package com.shiksha.recipesAPI.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ingredient")
public class KeyIngredient implements Serializable {

	private static final long serialVersionUID = -611915660699474521L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String info;

	public KeyIngredient(String name) {
		this.name = name;
	}

}
