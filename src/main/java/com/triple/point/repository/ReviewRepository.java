package com.triple.point.repository;

import com.triple.point.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review, String> {

	Optional<Review> findByIdAndIsDeletedFalse(String reviewId);

	Optional<Review> findByIdAndIsDeletedTrue(String reviewId);

	List<Review> findByPlaceIdAndIsDeletedFalseOrderByCreateDateTime(String placeId);
}
