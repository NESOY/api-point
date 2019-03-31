package com.triple.point.controller;

import com.triple.point.dto.EventDto;
import com.triple.point.service.PointService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/points")
public class PointController {
	private final PointService pointService;

	public PointController(PointService pointService) {
		this.pointService = pointService;
	}

	@GetMapping("/users/{userId}")
	public long getUserPoint(@PathVariable String userId) {
		return pointService.getUserPoint(userId);
	}

	@PostMapping
	public void event(@RequestBody EventDto eventDto) {
		pointService.routing(eventDto);
	}
}