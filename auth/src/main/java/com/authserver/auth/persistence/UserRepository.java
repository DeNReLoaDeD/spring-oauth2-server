package com.authserver.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authserver.auth.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username); 

}
