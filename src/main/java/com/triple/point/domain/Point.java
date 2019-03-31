package com.triple.point.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

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

	@Column
	private String content;

	@Column
	private Long value = 0L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private PointType pointType;

	@Column(nullable = false)
	private LocalDateTime createDatetime;

	@Column(nullable = false)
	private LocalDateTime updateDatetime;

	@Builder
	public Point(String reviewId, User user, PointType pointType, String content) {
		this.reviewId = reviewId;
		this.user = user;
		this.pointType = pointType;
		this.content = content;
		this.createDatetime = LocalDateTime.now();
		this.updateDatetime = LocalDateTime.now();
		this.value = getPoint();
	}


	public Long getPoint() {
		Long sumOfPoint = 0L;

		if (Strings.isNotBlank(content))
			sumOfPoint += 1L;

		return sumOfPoint;
	}
}
