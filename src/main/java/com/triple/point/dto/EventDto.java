package com.triple.point.dto;

import com.triple.point.domain.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class EventDto {
	private PointType type;
	private ActionType action;
	private String reviewId;
	private String content;
	private String userId;
	private String placeId;
	private List<String> attachedPhotoIds;

	public Point toPointEntity() {
		return Point.builder()
				.pointType(type)
				.review(new Review(reviewId, content))
				.user(new User(userId))
				.build();
	}
}
