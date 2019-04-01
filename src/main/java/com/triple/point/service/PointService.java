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
				modifyPoint(eventDto);
			case DELETE:
				deletePoint(eventDto);
		}
	}

	@Transactional
	public void modifyPoint(EventDto eventDto) {
		// 글만 있는 경우 사진을 추가하면 +1
		// 사진만 있는 경우 글을 추가하면 +1
		// 글과 사진이 모두 있는 경우 사진을 모두 삭제하면 -1
		// 글과 사진이 모두 있는 경우 글을 모두 삭제하면 -1
	}

	@Transactional
	public void deletePoint(EventDto eventDto) { // 리뷰 삭제 후 Event
		Optional<Review> findReview = reviewRepository.findByIdAndIsDeletedTrue(eventDto.getReviewId());

		if (findReview.isPresent()) {
			Review deletedReview = findReview.get();

			Optional<Point> point = pointRepository.findByReviewId(deletedReview.getId());
			point.ifPresent(pointRepository::delete);
			// 로그로 남긴다.

			if (deletedReview.isFirstReview()) {
				Optional<Review> firstPlaceReview = getFirstPlaceReview(deletedReview.getPlace().getId());

				firstPlaceReview.ifPresent(review -> {
					Point updatePoint = Point.builder()
							.pointType(PointType.REVIEW) // Bonus
							.review(review)
							.user(eventDto.toUserEntity())
							.build();

					pointRepository.save(updatePoint);
					// 로그로 남긴다.
				});
			}
		}
	}

	@Transactional
	public void savePoint(EventDto eventDto) { // Review 생성 후 Event
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
