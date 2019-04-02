package com.triple.point.domain;

import com.triple.point.fixture.ReviewFixture;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointTest extends ReviewFixture {
	private static final long BONUS_POINT = 1L;

	@Test
	public void getPoint_컨텐츠가_1자_이상인_경우_점수를_반환한다() {
		review.setContent("CONTENT");
		Point validContentPoint = Point.builder().review(review).build();
		assertEquals(1L + BONUS_POINT, (long) validContentPoint.getValue());

		review.setContent("");
		Point invalidContentPoint = Point.builder().review(review).build();
		assertEquals(BONUS_POINT, (long) invalidContentPoint.getValue());
	}

	@Test
	public void getPoint_사진이_1장_이상인_경우_점수를_반환한다() {
		List<Photo> photoList = new ArrayList<>();
		photoList.add(new Photo());
		review.setPhotoList(photoList);
		Point validPhotoPoint = Point.builder().review(review).build();
		assertEquals(1L + BONUS_POINT, (long) validPhotoPoint.getValue());

		review.setPhotoList(emptyList());
		Point invalidPhotoPoint = Point.builder().review(review).build();
		assertEquals(BONUS_POINT, (long) invalidPhotoPoint.getValue());
	}

	@Test
	public void getPoint_리뷰가_첫번째인_경우_점수를_반환한다() {
		Point point = Point.builder().review(review).build();

		assertTrue(review.isFirstReview());
		assertEquals(BONUS_POINT, (long) point.getValue());
	}
}