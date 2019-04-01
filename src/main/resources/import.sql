-- User
insert into user(user_id) values ('3ede0ef2-92b7-4817-a5f3-0c575361f7425');

-- Review
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('240a0658-dc5f-4878-9381-ebb7b2667772', '2e4baf1c-5acb-4efb-a1af-eddada31b00f', '좋아요!', '2019-03-31 23:20:33', now(), false )
insert into review(id, place_id, content, create_date_time, update_date_time, is_deleted) values('240a0658-dc5f-4878-9381-ebb7b2667773', '2e4baf1c-5acb-4efb-a1af-eddada31b00f', '좋아요!22', '2019-03-31 23:40:33', now(), false )

-- Photo
insert into photo(review_id, id) values('240a0658-dc5f-4878-9381-ebb7b2667772', 'e4d1a64e-a531-46de-88d0-ff0ed70c0bb8')
insert into photo(review_id, id) values('240a0658-dc5f-4878-9381-ebb7b2667772', 'afb0cef2-851d-4a50-bb07-9cc15cbdc332')

insert into photo(review_id, id) values('240a0658-dc5f-4878-9381-ebb7b2667773', 'afb0cef2-851d-4a50-bb07-9cc15cbdc333')

-- Place
insert into place(id) values('2e4baf1c-5acb-4efb-a1af-eddada31b00f')