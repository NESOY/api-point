package com.triple.point.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Review {
	@Id
	private String reviewId;

	@Column
	private String content;

	@Column(nullable = false)
	private LocalDateTime createDateTime;

	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@OneToOne(mappedBy = "review")
	private Point point;

	@OneToMany(mappedBy = "review")
	private List<Photo> photoList = new ArrayList<>();

	public Review(String reviewId, String content) {
		this.reviewId = reviewId;
		this.content = content;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
	}

	public Review(String reviewId, String content, List<Photo> photoList) {
		this.reviewId = reviewId;
		this.content = content;
		this.photoList = photoList;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
	}
}
