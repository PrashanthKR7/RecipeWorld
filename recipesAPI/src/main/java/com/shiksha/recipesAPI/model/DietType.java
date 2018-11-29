package com.shiksha.recipesAPI.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "diettype")
public class DietType implements Serializable {
	
	private static final long serialVersionUID = 2307496459741279518L;
	@Id
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String info;

}
