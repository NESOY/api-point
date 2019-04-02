package com.triple.point.domain;

public enum EventType {
	REVIEW("REVIEW");

	private String pointType;

	EventType(String value) {
		this.pointType = value;
	}
}
