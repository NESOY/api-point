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
				addPoint(eventDto);
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
			point.get().update();
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
				updatePoint.get().update();
				updatePoint.ifPresent(pointRepository::save);
				// 로그로 남긴다.
			});
		}

	}

	public void addPoint(EventDto eventDto) {
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


	private Optional<Review> getFirstPlaceReview(String placeId) {
		List<Review> reviewList = reviewRepository.findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(placeId);
		return Optional.of(reviewList.get(0));
	}
}
