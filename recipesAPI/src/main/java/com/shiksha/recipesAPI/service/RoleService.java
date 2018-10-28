package com.shiksha.recipesAPI.service;

import java.util.Optional;

import com.shiksha.recipesAPI.model.Role;
import com.shiksha.recipesAPI.model.RoleName;

public interface RoleService {

	Optional<Role> findByName(RoleName roleUser);

}
