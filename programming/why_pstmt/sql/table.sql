CREATE TABLE test_user(
  test_user_id int unsigned auto_increment primary key,
-- auto_increment는 primary key 필요
  username varchar(255) unique not null,
  password varchar(255) not null,
  created_at timestamp default CURRENT_TIMESTAMP
);