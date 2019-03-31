package com.triple.point.service;

import com.triple.point.domain.Point;
import com.triple.point.domain.User;
import com.triple.point.dto.EventDto;
import com.triple.point.repository.PointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointService {
	private PointRepository pointRepository;

	public PointService(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
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
		pointRepository.save(new Point(eventDto.getReviewId(), new User(eventDto.getUserId()), eventDto.getType()));
	}

	public int getUserPoint(String userId) {
		List<Point> pointList = pointRepository.findByUser(new User(userId));
		return pointList.size();
	}
}
