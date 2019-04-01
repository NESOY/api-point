package com.triple.point.repository;

import com.triple.point.domain.Place;
import com.triple.point.domain.Point;
import com.triple.point.domain.PointType;
import com.triple.point.domain.Review;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointRepositoryTest {
	@Autowired
	private PointRepository pointRepository;

	@After
	public void cleanup() {
		pointRepository.deleteAll();
	}

	@Test
	// Test Case dependency High
	public void ReviewID로_포인트를_검색할_수_있다() {
		String reviewId = "240a0658-dc5f-4878-9381-ebb7b2667772";
		Review review = Review.builder().id(reviewId).place(new Place("2e4baf1c-5acb-4efb-a1af-eddada31b00f")).build();
		Point point = Point.builder().review(review).pointType(PointType.REVIEW).build();
		pointRepository.save(point);

		Optional<Point> result = pointRepository.findByReviewId(reviewId);

		assertTrue(result.isPresent());
	}
}