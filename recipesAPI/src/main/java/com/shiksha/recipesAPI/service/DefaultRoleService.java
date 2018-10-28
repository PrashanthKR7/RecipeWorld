package com.shiksha.recipesAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipesAPI.model.Role;
import com.shiksha.recipesAPI.model.RoleName;
import com.shiksha.recipesAPI.repository.RoleRepository;

@Service
public class DefaultRoleService implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Optional<Role> findByName(RoleName roleName) {
		return roleRepository.findByName(roleName);
	}

}
