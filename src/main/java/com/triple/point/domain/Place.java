package com.triple.point.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Place {
	@Id
	private String placeId;

	@OneToMany(mappedBy = "place")
	private List<Review> reviewList = new ArrayList<>();

	public Place(String placeId) {
		this.placeId = placeId;
	}

	public boolean isFirstReview(Review review) {
		return reviewList.isEmpty() || reviewList.get(0).equals(review);
	}

	public void addReview(Review review) {
		reviewList.add(review);
	}
}
