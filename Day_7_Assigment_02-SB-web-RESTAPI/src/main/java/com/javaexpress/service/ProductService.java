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
		productRepository.save(product);
	}

	public Product fetchProductInfo(long productId) {
		log.info("fetchProductInfo::findCategoryById() {}", productId);
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
	}

	public List<Product> fetchProducts(String name) {
		return productRepository.findByCategoryName(name);
	}

	public void deleteProduct(long productId) {
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
		} else {
			throw new RuntimeException("Product Not Found");

		}

	}

	public void updateProduct(long productId, Product product) {
		Category category = categoryService.findCategoryById(product.getCategory().getId());
		if (category != null) {
		Product dbProduct = fetchProductInfo(productId);
		dbProduct.setName(product.getName());
		dbProduct.setDescription(product.getDescription());
		productRepository.save(dbProduct);
		}
	}
}
