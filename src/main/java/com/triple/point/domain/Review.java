package com.triple.point.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Review {
	@Id
	private String reviewId;

	@Column
	private String content;

	@Column(nullable = false)
	private LocalDateTime createDateTime;

	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@OneToOne(mappedBy = "review")
	private Point point;

	public Review(String reviewId) {
		this.reviewId = reviewId;
	}
}
