package com.shiksha.recipeAPI.dao;

import java.util.Optional;

import com.shiksha.recipesAPI.model.Role;
import com.shiksha.recipesAPI.model.RoleName;

public interface RoleDAO {

	Optional<Role> findByName(RoleName roleUser);

}
