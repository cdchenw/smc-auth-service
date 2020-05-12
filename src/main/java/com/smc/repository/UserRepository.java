package com.smc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.smc.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findOneWithAuthoritiesByUsername(String userName);

	Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);
}