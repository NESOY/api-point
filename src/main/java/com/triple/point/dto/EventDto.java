package com.triple.point.dto;

import com.triple.point.domain.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static java.util.stream.Collectors.toList;


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
		List<Photo> photoList = attachedPhotoIds.stream().map(Photo::new).collect(toList());

		return Point.builder()
				.pointType(type)
				.review(new Review(reviewId, content, photoList, new Place(placeId)))
				.user(new User(userId))
				.build();
	}
}
