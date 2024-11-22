package com.example.demo3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo3.models.User;

public interface UserRepository extends JpaRepository <User , Long>{
	 @Query("SELECT u FROM User u WHERE u.name = :username ")
	 Optional<User> findUserByUsernameAndEmail(@Param("username") String username);

}
