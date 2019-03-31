package com.triple.point.repository;

import com.triple.point.domain.Point;
import com.triple.point.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
	List<Point> findByUser(User user);
}
