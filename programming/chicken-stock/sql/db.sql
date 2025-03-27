CREATE TABLE accounts
(
    account_id int primary key auto_increment,
    nickname varchar(255) not null unique
);

INSERT INTO accounts (nickname) values ('진소희'),('진또잉'),('진뙹잉'),('진뜅잉')