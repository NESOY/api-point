package com.triple.point.domain;

public enum PointType {
	PHOTO("PHOTO"),
	CONTENT("CONTENT"),
	FIRST_REVIEW("FIRST_REVIEW");

	private String pointType;
	private String value;

	PointType(String pointType) {
		this.pointType = pointType;
	}
}
