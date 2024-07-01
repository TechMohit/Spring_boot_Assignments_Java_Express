package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaexpress.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p INNER JOIN p.category c where c.name=:categoryName")
	List<Product> fetchProductsByCategoryName(String categoryName);

	List<Product> findByCategoryName(String categoryName);

	@Query("select p from Product p where p.barCode=:barCode")
	Product fetchProductUsingJPQL(String barCode);

	Product findByBarCode(String barCode);

	@Query(value = "select * from product p where p.bar_code=:barCode", nativeQuery = true)
	Product fetchProductUsingNative(String barCode);

}
