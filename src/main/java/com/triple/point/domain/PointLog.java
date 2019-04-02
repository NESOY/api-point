package com.triple.point.domain;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
		name = "point_log",
		indexes = {
				@Index(name = "point_index", columnList = "point_id"),
				@Index(name = "review_index", columnList = "review_id"),
				@Index(name = "user_index", columnList = "user_id")
		})
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
