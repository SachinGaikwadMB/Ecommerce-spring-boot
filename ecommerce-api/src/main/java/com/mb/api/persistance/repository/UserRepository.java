package com.mb.api.persistance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.api.persistance.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
	User findByEmail(String email);

}
