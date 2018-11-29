package com.shiksha.recipesAPI.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Data;

@Data
public class BaseModel {
	@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class),
			@TypeDef(name = "string-array", typeClass = StringArrayType.class) })
	@MappedSuperclass
	public class BaseEntity {
	}
}
