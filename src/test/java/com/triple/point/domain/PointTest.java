package com.triple.point.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class PointTest {

	@Test
	public void getPoint_컨텐츠가_1자_이상인_경우_점수를_반환한다() {
		Point validContentPoint = Point.builder().review(new Review("VALID-REVIEW", "Nesoy")).build();
		assertEquals(1L, (long) validContentPoint.getPoint());

		Point invalidContentPoint = Point.builder().review(new Review("INVALID-REVIEW", "")).build();
		assertEquals(0L, (long) invalidContentPoint.getPoint());
	}

	@Test
	public void getPoint_사진이_1장_이상인_경우_점수를_반환한다() {
		List<Photo> photoList = new ArrayList<>();
		photoList.add(new Photo());
		Point validPhotoPoint = Point.builder().review(new Review("VALID-REVIEW", "123", photoList)).build();
		assertEquals(2L, (long) validPhotoPoint.getPoint());

		Point invalidPhotoPoint = Point.builder().review(new Review("VALID-REVIEW", "123", emptyList())).build();
		assertEquals(1L, (long) invalidPhotoPoint.getPoint());
	}
}