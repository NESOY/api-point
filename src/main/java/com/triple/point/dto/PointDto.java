package com.triple.point.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointDto {
	private String userId;
	private long point;

	@Builder
	public PointDto(String userId, long point) {
		this.userId = userId;
		this.point = point;
	}
}
