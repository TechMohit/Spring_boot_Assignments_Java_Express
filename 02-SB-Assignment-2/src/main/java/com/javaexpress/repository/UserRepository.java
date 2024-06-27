package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	

}
