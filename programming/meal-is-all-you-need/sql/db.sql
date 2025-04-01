CREATE TABLE animes(
    anime_id varchar(255) primary key,
    title varchar(255) unique not null,
    description varchar(255) not null,
    created_at timestamp default current_timestamp
);

CREATE TABLE anime_votes (
    anime_vote_id int unsigned primary key auto_increment,
    anime_id varchar(255) references animes(anime_id),
    created_at timestamp default current_timestamp
);