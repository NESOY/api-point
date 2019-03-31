package com.triple.point.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlaceTest {

	@Test
	public void isFirstReview_첫번째리뷰를_확인할수있다() {
		Review firstReview = new Review();
		Review secondReview = new Review();
		Place place = new Place("PLACE_ID");

		place.addReview(firstReview);

		assertTrue(place.isFirstReview(firstReview));
		assertFalse(place.isFirstReview(secondReview));
	}

	@Test
	public void isFirstReview_리뷰가없는경우_첫번째리뷰로_확인할수있다() {
		Review firstReview = new Review();
		Place place = new Place("PLACE_ID");

		assertTrue(place.isFirstReview(firstReview));
	}
}