package com.triple.point.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Point {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pointId;

	@Column(nullable = false)
	private String reviewId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private PointType eventType;

	@Column(nullable = false)
	private LocalDateTime createDatetime;

	@Column(nullable = false)
	private LocalDateTime updateDatetime;

	public Point(String reviewId, User user, PointType eventType) {
		this.reviewId = reviewId;
		this.user = user;
		this.eventType = eventType;
		this.createDatetime = LocalDateTime.now();
		this.updateDatetime = LocalDateTime.now();
	}
}
