# api-point

## DDL
### Review
```sql
CREATE TABLE `review` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date_time` datetime NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_date_time` datetime NOT NULL,
  `place_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `place_index` (`place_id`)
)
```

### Point
```sql
CREATE TABLE `point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime NOT NULL,
  `update_date_time` datetime NOT NULL,
  `value` bigint(20) DEFAULT NULL,
  `review_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `review_index` (`review_id`),
  KEY `user_index` (`user_id`)
)
```

### Point Log
```sql
CREATE TABLE `point_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_time` datetime NOT NULL,
  `point_type` int(11) NOT NULL,
  `value` bigint(20) NOT NULL,
  `point_id` bigint(20) DEFAULT NULL,
  `review_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `point_index` (`point_id`),
  KEY `review_index` (`review_id`),
  KEY `user_index` (`user_id`)
)
```

### User
```sql
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)
```

### Place
```sql
CREATE TABLE `place` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)
```

### Photo
```sql
CREATE TABLE `photo` (
  `id` varchar(255) NOT NULL,
  `review_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `review_index` (`review_id`)
)
```
## API
- POST /events
    - event 처리
- POST /points
    - points 조회
    
## Package
```
java/com/triple/point/
├── PointApplication.java
├── controller
│   └── PointController.java
├── domain
│   ├── ActionType.java
│   ├── EventType.java
│   ├── Photo.java
│   ├── Place.java
│   ├── Point.java
│   ├── PointLog.java
│   ├── PointType.java
│   ├── Review.java
│   └── User.java
├── dto
│   ├── EventDto.java
│   ├── EventResponseDto.java
│   └── PointDto.java
├── repository
│   ├── PointLogRepository.java
│   ├── PointRepository.java
│   └── ReviewRepository.java
└── service
    └── PointService.java
```