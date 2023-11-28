package com.net.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.net.entity.User;

/*
 * No need to give @repository annotation as the simplejpaRepository already
 * have Repository annoted
 */
//@Repository 
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
