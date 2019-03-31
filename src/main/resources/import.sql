-- User
insert into user(user_id) values ('3ede0ef2-92b7-4817-a5f3-0c575361f7425');

-- Review
insert into review(review_id, content, create_date_time, update_date_time) values('240a0658-dc5f-4878-9381-ebb7b2667772', '좋아요!', now(), now())


-- Photo
insert into photo(review_id, photo_id) values('240a0658-dc5f-4878-9381-ebb7b2667772', 'e4d1a64e-a531-46de-88d0-ff0ed70c0bb8')
insert into photo(review_id, photo_id) values('240a0658-dc5f-4878-9381-ebb7b2667772', 'afb0cef2-851d-4a50-bb07-9cc15cbdc332')