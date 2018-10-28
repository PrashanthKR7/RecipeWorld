package com.shiksha.recipesAPI.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiksha.recipesAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailId(Serializable emailId);

	Boolean existsByUsername(String username);

	Boolean existsByEmailId(String emailId);

	Optional<User> findByUsernameOrEmailId(String username, String emailId);

	Optional<User> findByUsername(String username);
}
