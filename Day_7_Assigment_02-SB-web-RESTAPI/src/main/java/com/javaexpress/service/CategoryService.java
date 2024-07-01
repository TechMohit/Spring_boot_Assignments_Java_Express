package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;
import com.javaexpress.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		log.info("CategoryService::createCategory() {}", category.getName());
		categoryRepository.save(category);
		log.info("category Successfully save in DB");
	}

	public List<Category> fetchAllCategory() {
		log.info("CategoryService::fetchAllCategory");
		List<Category> categoryList = categoryRepository.findAll();
		List<Category> result = categoryList.stream().sorted(Comparator.comparing(Category::getName)).toList();
		return result;
	}

	public Category findCategoryById(long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
	}

	public void updateCategory(Long categoryId, Category category) {
		log.info("CategoryService::updateCategory() {}", categoryId);
		Category dbCategory = findCategoryById(categoryId);
		dbCategory.setName(category.getName());
		categoryRepository.save(dbCategory);
		log.info("update category successfully in DB");
	}

	public void deleteCategory(Long categoryId) {
		log.info("CategoryService::deleteCategory() {}", categoryId);
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
			log.info("delete category successfully in DB");
		} else {
			throw new RuntimeException(" Category Not Found");
		}
	}

}
