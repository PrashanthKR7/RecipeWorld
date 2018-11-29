package com.shiksha.recipesAPI.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Ingredient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5208003236820623290L;
	private int id;
	private String name;
	private String quantity;

	public Ingredient(int id, String name, String quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public Ingredient(String name) {
		this.name = name;
	}

}
