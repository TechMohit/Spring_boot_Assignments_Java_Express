package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	

}
