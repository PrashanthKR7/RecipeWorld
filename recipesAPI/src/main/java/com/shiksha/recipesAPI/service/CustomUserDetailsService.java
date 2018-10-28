package com.shiksha.recipesAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiksha.recipesAPI.exception.ResourceNotFoundException;
import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userService.findByUsernameOrEmailId(usernameOrEmail, usernameOrEmail).orElseThrow(() -> {
			throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
		});

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userService.getById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("User", "id", id);
		});
		return UserPrincipal.create(user);
	}

}
