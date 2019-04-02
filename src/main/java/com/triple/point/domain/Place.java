package com.triple.point.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Getter
@NoArgsConstructor
public class Place {
	@Id
	private String id;
	@OneToMany(mappedBy = "place")
	@OrderBy("create_date_time ASC")
	private List<Review> reviewList = new ArrayList<>();

	public Place(String id) {
		this.id = id;
	}

	public boolean isFirstReview(Review review) {
		Review firstReview = reviewList.stream()
				.filter(Review::isNotDeleted)
				.findFirst().orElse(review);

		return firstReview.equals(review);
	}

	public boolean isPastFirstReview(Review review) {
		List<Review> deletedReviewList = reviewList.stream()
				.filter(Review::isDeleted)
				.collect(toList());
		int lastReviewIndex = deletedReviewList.size() - 1;

		return deletedReviewList.get(lastReviewIndex).equals(review);
	}

	public void addReview(Review review) {
		reviewList.add(review);
	}
}
