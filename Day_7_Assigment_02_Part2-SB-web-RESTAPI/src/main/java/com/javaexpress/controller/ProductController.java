package com.javaexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javaexpress.entities.Product;
import com.javaexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public void createProduct(@RequestBody Product product) {
		log.info("ProductController ::CreateCatogry{}", product.getName());
		productService.createProduct(product);
		log.info("Product created successfully");
	}

	// http://localhost:8080/api/v1/product/1
	@GetMapping("{pId}")
	public Product fetchProduct(@PathVariable(name = "pId") long productId) {
		log.info("ProductController ::fetchProduct{}", productId);
		return productService.fetchProductInfo(productId);
	}

	@GetMapping("Product/{name}")
	public List<Product> fetchProductBasedOnProductName(@PathVariable String name) {
		log.info("ProductController ::fetchProductBasedOnProductName{}", name);
		return productService.fetchProducts(name);
	}

	@DeleteMapping("{pId}")
	public void deleteProduct(@PathVariable(name = "pId") long productId) {
		log.info("ProductController ::deleteProduct{}", productId);
		productService.deleteProduct(productId);
		log.info("Product deleted successfully");
	}

	@PutMapping("{pId}")
	public void updateProduct(@PathVariable(name = "pId") long productId, @RequestBody Product product) {
		log.info("ProductController ::updateProduct{}", productId);
		productService.updateProduct(productId, product);
		log.info("Product updated successfully");
	}
}
