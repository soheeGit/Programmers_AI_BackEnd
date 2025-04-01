CREATE TABLE SEARCH_RESULT_BOOKMARK (
    bookmark_id varchar(255) primary key default gen_random_uuid(),
    title varchar(255) not null,
    link varchar(2000) not null,
    description varchar(2000) not null,
    date varchar(255) not null,
    created_at timestamp default current_timestamp
);