package com.triple.point.controller;

import com.triple.point.dto.EventDto;
import com.triple.point.dto.EventResponseDto;
import com.triple.point.dto.PointDto;
import com.triple.point.service.PointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {
	private final PointService pointService;

	public PointController(PointService pointService) {
		this.pointService = pointService;
	}

	@PostMapping("/events")
	public EventResponseDto event(@RequestBody EventDto eventDto) {
		pointService.handleEvent(eventDto);

		return new EventResponseDto(eventDto);
	}

	@PostMapping("/points")
	public PointDto getPoints(@RequestBody PointDto pointDto) {
		return pointService.getPoint(pointDto);
	}
}