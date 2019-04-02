package com.triple.point.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Review {
	@Id
	private String id;

	@Column
	private String content;

	@Column
	private Boolean isDeleted;

	@Column(nullable = false)
	private LocalDateTime createDateTime;

	@Column(nullable = false)
	private LocalDateTime updateDateTime;

	@OneToOne(mappedBy = "review")
	private Point point;

	@OneToMany(mappedBy = "review")
	private List<Photo> photoList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;

	public Review(String id, String content) {
		this.id = id;
		this.content = content;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
		this.isDeleted = false;
	}

	public Review(String id, String content, List<Photo> photoList) {
		this.id = id;
		this.content = content;
		this.photoList = photoList;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
		this.isDeleted = false;
	}

	@Builder
	public Review(String id, String content, List<Photo> photoList, Place place, Boolean isDeleted) {
		this.id = id;
		this.content = content;
		this.photoList = photoList;
		this.place = place;
		this.isDeleted = isDeleted;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
	}

	public boolean isFirstReview() {
		return place.isFirstReview(this);
	}

	public boolean isPastFirstReview() {
		return place.isPastFirstReview(this);
	}

	public void deleteReview() {
		this.isDeleted = true;
	}

	public Boolean isNotDeleted() {
		return !isDeleted;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}
}
