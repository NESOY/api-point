package com.triple.point.domain;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PointLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "point_id")
	private Point point;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private LocalDateTime logTime;

	@Column(nullable = false)
	private Long value;

	@Column(nullable = false)
	private PointType pointType;

	@Builder
	public PointLog(Point point, Review review, User user, Long value, PointType pointType) {
		this.point = point;
		this.review = review;
		this.user = user;
		this.value = value;
		this.pointType = pointType;
		this.logTime = LocalDateTime.now();
	}
}
