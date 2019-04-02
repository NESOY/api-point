package com.triple.point.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;


@Entity
@Getter
@NoArgsConstructor
public class Point {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pointId;

	@Column
	private Long value = 0L;

	@Column(nullable = false)
	private PointType pointType;

	@Column(nullable = false)
	private LocalDateTime createDateTime;

	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@OneToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Point(Review review, User user, PointType pointType) {
		this.review = review;
		this.user = user;
		this.pointType = pointType;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
		updatePoint();
	}

	public void updatePoint() {
		Long sumOfPoint = 0L;

		if (isNotBlank(review.getContent()))
			sumOfPoint += 1L;

		if (isNotEmpty(review.getPhotoList()))
			sumOfPoint += 1L;

		if (review.isFirstReview())
			sumOfPoint += 1L;

		this.value = sumOfPoint;
	}
}
