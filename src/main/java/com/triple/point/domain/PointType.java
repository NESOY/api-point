package com.triple.point.domain;

public enum PointType {
	NORMAL("NORMAL"),
	BONUS("BONUS");

	private String pointType;

	PointType(String value) {
		this.pointType = value;
	}
}
