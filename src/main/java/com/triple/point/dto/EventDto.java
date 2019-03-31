package com.triple.point.dto;

import com.triple.point.domain.ActionType;
import com.triple.point.domain.PointType;
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
}
