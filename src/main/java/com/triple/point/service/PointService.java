package com.triple.point.service;

import com.triple.point.domain.Point;
import com.triple.point.domain.Review;
import com.triple.point.dto.EventDto;
import com.triple.point.dto.PointDto;
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

	public void handleEvent(EventDto eventDto) {
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
	// partition Update
	public void modifyPoint(EventDto eventDto) {
		Optional<Review> findReview = reviewRepository.findByIdAndIsDeletedFalse(eventDto.getReviewId());
		if (findReview.isPresent()) {
			Review modifiedReview = findReview.get();

			Optional<Point> point = pointRepository.findByReviewId(modifiedReview.getId());
			point.get().updatePoint();
			point.ifPresent(pointRepository::save);
			// 로그
		}
	}

	@Transactional
	public void deletePoint(EventDto eventDto) {
		Review deletedReview = reviewRepository.findByIdAndIsDeletedTrue(eventDto.getReviewId())
				.orElseThrow(InvalidParameterException::new);

		Optional<Point> point = pointRepository.findByReviewId(deletedReview.getId());
		point.ifPresent(pointRepository::delete);
		// Save PointLog

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
					.review(review.get())
					.user(eventDto.toUserEntity())
					.build();

			pointRepository.save(point);
			// 로그를 남긴다.
		}
	}

	public PointDto getPoint(PointDto pointDto) {
		List<Point> pointList = pointRepository.findByUserId(pointDto.getUserId());

		long userPoint = pointList.stream().mapToLong(Point::getValue).sum();

		return PointDto.builder().userId(pointDto.getUserId()).point(userPoint).build();
	}


	private Optional<Review> getFirstPlaceReview(String placeId) {
		List<Review> reviewList = reviewRepository.findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(placeId);
		return Optional.of(reviewList.get(0));
	}
}
