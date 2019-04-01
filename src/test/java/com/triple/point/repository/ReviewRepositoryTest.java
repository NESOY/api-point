package com.triple.point.repository;

import com.triple.point.domain.Review;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {
	private static final String REVIEW_ID = "ID";
	@Autowired
	private ReviewRepository reviewRepository;

	@After
	public void cleanup() {
		reviewRepository.deleteAll();
	}

	@Test
	public void findById_ReviewID로_Review를_검색할수있다() {
		Review review = Review.builder().id(REVIEW_ID).isDeleted(false).build();
		reviewRepository.save(review);

		Optional<Review> result = reviewRepository.findById(REVIEW_ID);

		assertEquals(REVIEW_ID, result.get().getId());
	}

	@Test
	public void findByIdAndIsDeletedFalse_삭제된_리뷰는_검색이_불가능하다() {
		Review deleteReview = Review.builder().id(REVIEW_ID).isDeleted(true).build();
		reviewRepository.save(deleteReview);

		Optional<Review> deleteResult = reviewRepository.findByIdAndIsDeletedFalse(REVIEW_ID);

		assertFalse(deleteResult.isPresent());
	}

	@Test
	public void findByIdAndIsDeletedFalse_삭제되지않는_리뷰는_검색이_가능하다() {
		Review validReview = Review.builder().id(REVIEW_ID).isDeleted(false).build();
		reviewRepository.save(validReview);

		Optional<Review> validResult = reviewRepository.findByIdAndIsDeletedFalse(REVIEW_ID);

		assertTrue(validResult.isPresent());
		assertEquals(REVIEW_ID, validResult.get().getId());
	}

	@Test
	public void 리뷰를_삭제처리하고_저장할수있다() {
		Review validReview = Review.builder().id(REVIEW_ID).isDeleted(false).build();
		reviewRepository.save(validReview);
		Optional<Review> validResult = reviewRepository.findByIdAndIsDeletedFalse(REVIEW_ID);

		validResult.ifPresent(Review::deleteReview);
		reviewRepository.save(validResult.get());

		Optional<Review> result = reviewRepository.findById(REVIEW_ID);
		assertTrue(result.get().getIsDeleted());
	}

	@Test
	public void findByIdAndIsDeletedTrue_삭제된_리뷰를_검색이_가능하다() {
		Review validReview = Review.builder().id(REVIEW_ID).isDeleted(true).build();
		reviewRepository.save(validReview);

		Optional<Review> validResult = reviewRepository.findByIdAndIsDeletedTrue(REVIEW_ID);

		assertTrue(validResult.isPresent());
		assertEquals(REVIEW_ID, validResult.get().getId());
	}

	@Test
	public void findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime_첫번째_리뷰_검색이_가능하다() {
		String placeId = "2e4baf1c-5acb-4efb-a1af-eddada31b00f";

		List<Review> reviewList = reviewRepository.findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(placeId);

		assertFalse(reviewList.isEmpty());
		// todo : assert CreateDateTime
	}
}