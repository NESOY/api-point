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

import java.security.InvalidParameterException;
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
				break;
			case MOD:
				modifyPoint(eventDto);
				break;
			case DELETE:
				deletePoint(eventDto);
				break;
		}
	}

	@Transactional
	public void modifyPoint(EventDto eventDto) {
		Optional<Review> findReview = reviewRepository.findByIdAndIsDeletedFalse(eventDto.getReviewId());
		if (findReview.isPresent()) {
			Review modifiedReview = findReview.get();

			Point modifiedPoint = Point.builder()
					.pointType(PointType.REVIEW)
					.review(modifiedReview)
					.user(eventDto.toUserEntity())
					.build();

			pointRepository.save(modifiedPoint);
			// 로그
		}
	}

	@Transactional
	public void deletePoint(EventDto eventDto) {
		Review deletedReview = reviewRepository.findByIdAndIsDeletedTrue(eventDto.getReviewId())
				.orElseThrow(InvalidParameterException::new);

		Optional<Point> point = pointRepository.findByReviewId(deletedReview.getId());
		point.ifPresent(pointRepository::delete);
		// Save Log
		
		if (deletedReview.isPastFirstReview()) {
			Optional<Review> firstPlaceReview = getFirstPlaceReview(deletedReview.getPlace().getId());

			firstPlaceReview.ifPresent(review -> {
				Optional<Point> updatePoint = pointRepository.findByReviewId(review.getId());
				updatePoint.get().updatePoint();
				updatePoint.ifPresent(pointRepository::save);
				// 로그로 남긴다.
			});
		}

	}

	@Transactional
	public void savePoint(EventDto eventDto) {
		Optional<Review> review = reviewRepository.findByIdAndIsDeletedFalse(eventDto.getReviewId());

		if (review.isPresent()) {
			Point point = Point.builder()
					.pointType(PointType.REVIEW)
					.review(review.get())
					.user(eventDto.toUserEntity())
					.build();

			pointRepository.save(point);
			// 로그를 남긴다.
		}
	}

	public long getUserPoint(String userId) {
		List<Point> pointList = pointRepository.findByUser(new User(userId));

		return pointList.stream().mapToLong(Point::getValue).sum();
	}


	private Optional<Review> getFirstPlaceReview(String placeId) {
		List<Review> reviewList = reviewRepository.findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(placeId);
		return Optional.of(reviewList.get(0));
	}
}
