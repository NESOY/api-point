package com.triple.point.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

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
		return reviewList.isEmpty() || reviewList.get(0).equals(review);
	}

	public void addReview(Review review) {
		reviewList.add(review);
	}
}
