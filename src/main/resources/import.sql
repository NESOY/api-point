-- User
insert into user(id) values ('NESOY');

-- Place
insert into place(id) values('SEOUL')
insert into place(id) values('BUSAN')

-- Photo
insert into photo(review_id, id) values('CONTENT_2_PHOTO_FIRST_REVIEW', 'PHOTO_1')
insert into photo(review_id, id) values('CONTENT_2_PHOTO_FIRST_REVIEW', 'PHOTO_2')
insert into photo(review_id, id) values('NO_CONTENT_ONLY_PHOTO_NOT_FIRST_REVIEW', 'PHOTO_3')

-- Review
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('CONTENT_2_PHOTO_FIRST_REVIEW', 'SEOUL', '좋아요!', '2019-03-31 23:20:33', now(), false )
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('NO_CONTENT_NO_PHOTO_NOT_FIRST_REVIEW', 'SEOUL', '', '2019-03-31 23:40:33', now(), false )
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('INCLUE_CONTENT_NO_PHOTO_NOT_FIRST_REVIEW', 'SEOUL', 'CONTENT', '2019-03-31 23:40:33', now(), false )
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('NO_CONTENT_ONLY_PHOTO_NOT_FIRST_REVIEW', 'SEOUL', '', '2019-03-31 23:40:33', now(), false )

insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('DELETED_REVIEW', 'BUSAN', 'CONTENT', '2019-03-31 23:00:00', now(), true )
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('REVIEW', 'BUSAN', 'CONTENT', '2019-03-31 23:40:33', now(), false )

-- Point
insert into point(review_id, user_id, value, create_date_time, update_date_time) values('DELETED_REVIEW', 'NESOY',  2, '2019-03-31 23:00:00', now())
insert into point(review_id, user_id, value, create_date_time, update_date_time) values('REVIEW', 'NESOY',  1, '2019-03-31 23:40:00', now())
insert into point(review_id, user_id, value, create_date_time, update_date_time) values('NO_CONTENT_ONLY_PHOTO_NOT_FIRST_REVIEW', 'NESOY',  2, '2019-03-31 23:40:00', now())