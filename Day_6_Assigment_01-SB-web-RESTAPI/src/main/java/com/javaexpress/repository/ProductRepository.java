package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
