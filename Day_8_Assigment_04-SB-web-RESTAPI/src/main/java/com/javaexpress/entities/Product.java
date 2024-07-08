package com.javaexpress.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double price;
	private Integer quantity;
	private String description;
	private Boolean isStock; // 1 or 0 ( true or false)
	private String barCode;

	// many to one
	@ManyToOne
	@JoinColumn(name = "cat_id", nullable = false)
	private Category category;

}
