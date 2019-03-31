package com.triple.point.domain;

public enum ActionType {
	ADD("ADD"),
	MOD("MOD"),
	DELETE("DELETE");

	private String actionType;

	ActionType(String value) {
		this.actionType = value;
	}
}
