package com.triple.point.repository;

import com.triple.point.domain.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends CrudRepository<Point, Long> {
	List<Point> findByUserId(String userId);

	Optional<Point> findByReviewId(String reviewId);
}
