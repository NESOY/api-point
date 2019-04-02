package com.triple.point.dto;

import com.triple.point.domain.ActionType;
import com.triple.point.domain.EventType;
import com.triple.point.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class EventDto {
	private EventType type;
	private ActionType action;
	private String reviewId;
	private String content;
	private String userId;
	private String placeId;
	private List<String> attachedPhotoIds;

	public User toUserEntity() {
		return new User(userId);
	}
}
