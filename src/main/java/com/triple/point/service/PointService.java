package com.triple.point.service;

import com.triple.point.domain.Point;
import com.triple.point.domain.PointLog;
import com.triple.point.domain.PointType;
import com.triple.point.domain.Review;
import com.triple.point.dto.EventDto;
import com.triple.point.dto.PointDto;
import com.triple.point.repository.PointLogRepository;
import com.triple.point.repository.PointRepository;
import com.triple.point.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PointService {
	private final PointRepository pointRepository;
	private final ReviewRepository reviewRepository;
	private final PointLogRepository pointLogRepository;

	public void handleEvent(EventDto eventDto) {
		switch (eventDto.getAction()) {
			case ADD:
				handleAddEvent(eventDto);
				break;
			case MOD:
				handleModifyEvent(eventDto);
				break;
			case DELETE:
				handleDeleteEvent(eventDto);
				break;
		}
	}

	public void handleModifyEvent(EventDto eventDto) {
		Optional<Review> findReview = reviewRepository.findByIdAndIsDeletedFalse(eventDto.getReviewId());
		if (findReview.isPresent()) {
			Review modifiedReview = findReview.get();
			Optional<Point> modifiedPoint = pointRepository.findByReviewId(modifiedReview.getId());
			modifiedPoint.get().update();
			modifyPoint(modifiedPoint);
		}
	}

	@Transactional
	public void modifyPoint(Optional<Point> modifiedPoint) {
		modifiedPoint.ifPresent(pointRepository::save);
		// Save Log
	}

	@Transactional
	public void handleDeleteEvent(EventDto eventDto) {
		Review deletedReview = reviewRepository.findByIdAndIsDeletedTrue(eventDto.getReviewId())
				.orElseThrow(InvalidParameterException::new);

		Optional<Point> deletePoint = pointRepository.findByReviewId(deletedReview.getId());
		Optional<Point> updatePoint = Optional.empty();

		if (deletedReview.isPastFirstReview()) {
			Optional<Review> firstPlaceReview = getFirstPlaceReview(deletedReview.getPlace().getId());
			updatePoint = pointRepository.findByReviewId(firstPlaceReview.get().getId());
			updatePoint.get().update();
		}

		deletePoint(deletePoint, updatePoint);
	}

	private Optional<Review> getFirstPlaceReview(String placeId) {
		List<Review> reviewList = reviewRepository.findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(placeId);
		return Optional.of(reviewList.get(0));
	}

	@Transactional
	public void deletePoint(Optional<Point> deletePoint, Optional<Point> updatePoint) {
		deletePoint.ifPresent(pointRepository::delete);
		// Save Log
		updatePoint.ifPresent(pointRepository::save);
		// Save Log
	}

	public void handleAddEvent(EventDto eventDto) {
		Optional<Review> review = reviewRepository.findByIdAndIsDeletedFalse(eventDto.getReviewId());
		if (review.isPresent()) {
			Point point = Point.builder().review(review.get()).user(eventDto.toUserEntity()).build();
			List<PointType> pointTypeList = point.getPointTypeList();
			List<PointLog> pointLogList = pointTypeList.stream().map(pointType -> PointLog.builder()
					.point(point)
					.review(review.get())
					.value(1L)
					.pointType(pointType)
					.user(eventDto.toUserEntity())
					.build()).collect(toList());

			savePoint(point, pointLogList);
		}
	}

	@Transactional
	public void savePoint(Point point, List<PointLog> pointLogList) {
		pointRepository.save(point);
		pointLogRepository.saveAll(pointLogList);
	}

	public PointDto getPoint(PointDto pointDto) {
		List<Point> pointList = pointRepository.findByUserId(pointDto.getUserId());

		long userPoint = pointList.stream().mapToLong(Point::getValue).sum();

		return PointDto.builder().userId(pointDto.getUserId()).point(userPoint).build();
	}

}
