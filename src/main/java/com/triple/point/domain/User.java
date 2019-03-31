package com.triple.point.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {
	@Id
	private String userId;

	@OneToMany(mappedBy = "user")
	private List<Point> pointList = new ArrayList<>();

	public User(String userId) {
		this.userId = userId;
	}
}
