package com.triple.point.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Photo {
	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	public Photo(String id) {
		this.id = id;
	}
}
