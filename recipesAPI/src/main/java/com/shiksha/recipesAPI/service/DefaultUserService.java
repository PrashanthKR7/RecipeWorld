package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipesAPI.model.User;
import com.shiksha.recipesAPI.repository.UserRepository;
@Service
public class DefaultUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getById(Serializable userId) {
		return userRepository.findById((Long) userId);
	}

	@Override
	public void delete(Serializable id) {
		userRepository.deleteById((Long) id);
	}

	@Override
	public Optional<User> findUserByEmailId(Serializable emailId) {
		return (emailId != null) ? userRepository.findByEmailId(emailId) : null;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUsernameOrEmailId(String username, String emailId) {
		return userRepository.findByUsernameOrEmailId(username, emailId);
	}

	@Override
	public List<User> findByIdIn(List<Long> userIds) {
		return userRepository.findAllById(userIds);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmailId(String emailId) {
		return userRepository.existsByEmailId(emailId);
	}

}
