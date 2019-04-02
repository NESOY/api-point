package com.triple.point.fixture;

import com.triple.point.domain.Place;
import com.triple.point.domain.Review;
import org.junit.Before;

public abstract class ReviewFixture {
	protected static final String VALID_REVIEW_ID = "VALID-REVIEW";
	protected static final String USER_ID = "NESOY";
	protected Review review;

	@Before
	public void setUp() {
		review = Review.builder().place(new Place()).id(VALID_REVIEW_ID).build();
	}
}
