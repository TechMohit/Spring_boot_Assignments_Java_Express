package com.javaexpress.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaexpress.entities.Category;
import com.javaexpress.entities.Product;
import com.javaexpress.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;

	public void createProduct(Product product) {
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		log.info("ProductService::createProduct {}", product.getName());
		productRepository.save(product);
		log.info("product Successfully save in DB");
	}

	public Product fetchProductInfo(long productId) {
		log.info("fetchProductInfo::fetchProductInfo() {}", productId);
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
	}

	public List<Product> fetchProducts(String name) {
		log.info("ProductService::fetchProducts() {}", name);
		return productRepository.findByCategoryName(name);
	}

	public void deleteProduct(long productId) {
		if (productRepository.existsById(productId)) {
			log.info("ProductService::deleteProduct() {}", productId);
			productRepository.deleteById(productId);
			log.info("product deleted successfully");
		} else {
			log.info("product doesnot exists");
			throw new RuntimeException("Product Not Found");
		}
	}

	public void updateProduct(long productId, Product product) {
		Category category = categoryService.findCategoryById(product.getCategory().getId());
		if (category != null) {
			log.info("ProductService::updateProduct() {}", category.getId());
			Product dbProduct = fetchProductInfo(productId);
			dbProduct.setName(product.getName());
			dbProduct.setDescription(product.getDescription());
			productRepository.save(dbProduct);
			log.info("product updated successfully");
		}
	}

	public List<Product> fetchProductsBasedOnCategory(String name) {
		log.info("ProductService::fetchProductsBasedOnCategory() {}", name);
		return productRepository.fetchProductsByCategoryName(name);
	}

	public Product fetchProductsBasedBarCodeViaJPQL(String inputBarcode) {
		log.info("ProductService::fetchProductsBasedBarCodeViaJPQL() {}", inputBarcode);
		return productRepository.fetchProductUsingJPQL(inputBarcode);
	}
}
