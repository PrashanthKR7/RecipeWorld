package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.shiksha.recipesAPI.model.User;

public interface UserService extends CRUDService<User> {
	public Optional<User> findUserByEmailId(Serializable emailId);
	
	public Optional<User> findByUsernameOrEmailId(String username, String emailId);

	public List<User> findByIdIn(List<Long> userIds);

	public Optional<User> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmailId(String emailId);
	
}
