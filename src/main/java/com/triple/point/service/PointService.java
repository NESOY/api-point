package com.triple.point.service;

import com.triple.point.domain.Point;
import com.triple.point.domain.PointType;
import com.triple.point.domain.Review;
import com.triple.point.domain.User;
import com.triple.point.dto.EventDto;
import com.triple.point.repository.PointRepository;
import com.triple.point.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PointService {
	private final PointRepository pointRepository;
	private final ReviewRepository reviewRepository;

	public PointService(PointRepository pointRepository, ReviewRepository reviewRepository) {
		this.pointRepository = pointRepository;
		this.reviewRepository = reviewRepository;
	}

	public void routing(EventDto eventDto) {
		switch (eventDto.getAction()) {
			case ADD:
				savePoint(eventDto);
			case MOD:
			case DELETE:
		}
	}

	@Transactional
	public void savePoint(EventDto eventDto) {
		Optional<Review> review = reviewRepository.findById(eventDto.getReviewId());

		Point point = Point.builder()
				.pointType(PointType.REVIEW)
				.review(review.get())
				.user(new User(eventDto.getUserId()))
				.build();

		pointRepository.save(point);
	}

	public long getUserPoint(String userId) {
		List<Point> pointList = pointRepository.findByUser(new User(userId));

		return pointList.stream().mapToLong(Point::getValue).sum();
	}
}
