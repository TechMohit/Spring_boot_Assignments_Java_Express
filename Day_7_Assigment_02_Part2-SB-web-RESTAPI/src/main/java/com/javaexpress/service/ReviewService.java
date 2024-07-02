package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaexpress.entities.Product;
import com.javaexpress.entities.Review;
import com.javaexpress.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;

	public void createReview(Review review) {
		log.info("ReviewService::createReview {}", review.getName());
		reviewRepository.save(review);
		log.info("review Successfully save in DB");
	}

	public Review fetchReviewById(long reviewId) {
		log.info("ReviewService::fetchReviewById() {}", reviewId);
		return reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review Not Found"));
	}

	public void deleteReview(long reviewId) {
		if (reviewRepository.existsById(reviewId)) {
			log.info("ReviewService::deleteReview() {}", reviewId);
			reviewRepository.deleteById(reviewId);
			log.info("Review deleted successfully");
		} else {
			log.info("product doesnot exists");
			throw new RuntimeException("Review Not Found");
		}
	}

	public void updateReview(long reviewId, Review review) {
		Product product = productService.fetchProductInfo(review.getProduct().getId());
		if (product != null) {
			log.info("ProductService::updateReview() {}", product.getId());
			Review dbReview = fetchReviewById(reviewId);
			dbReview.setName(review.getName());
			dbReview.setDescription(review.getDescription());
			reviewRepository.save(dbReview);
			log.info("review updated successfully");
		}
	}
}
