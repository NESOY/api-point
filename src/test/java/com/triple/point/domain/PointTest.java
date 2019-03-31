package com.triple.point.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {

	@Test
	public void getPoint_컨텐츠가_1자_이상인_경우_점수를_반환한다() {
		Point validContentPoint = Point.builder().review(new Review("VALID-REVIEW", "Nesoy")).build();
		assertEquals(1L, (long) validContentPoint.getPoint());

		Point invalidContentPoint = Point.builder().review(new Review("INVALID-REVIEW", "")).build();
		assertEquals(0L, (long) invalidContentPoint.getPoint());
	}
}