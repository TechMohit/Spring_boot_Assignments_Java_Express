package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Category;
import com.javaexpress.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public void createCategory(@RequestBody Category category) {
		log.info("CategoryController ::CreateCatogry{}", category.getName());
		categoryService.createCategory(category);
		log.info("Category created successfully");
	}

	// http://localhost:8080/api/v1/category/123
	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		log.info("Categorycontroller:: getCategoryId {}", categoryId);
		return categoryService.findCategoryById(categoryId);
	}

	@PutMapping("{categoryId}")
	public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		log.info("Categorycontroller:: updateCategory {} ", categoryId);
		categoryService.updateCategory(categoryId, category);
		log.info("Category updated successfully");
	}

	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		log.info("Categorycontroller:: deleteCategory {} ", categoryId);
		categoryService.deleteCategory(categoryId);
		log.info("Category deleted successfully");
	}
}