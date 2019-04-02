package com.triple.point.dto;

import com.triple.point.domain.ActionType;
import com.triple.point.domain.EventType;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@ResponseStatus(HttpStatus.OK)
public class EventResponseDto {
	private EventType type;
	private ActionType action;
	private String reviewId;
	private LocalDateTime time;

	public EventResponseDto(EventDto eventDto) {
		this.type = eventDto.getType();
		this.action = eventDto.getAction();
		this.reviewId = eventDto.getReviewId();
		this.time = LocalDateTime.now();
	}
}
