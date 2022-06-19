create schema if not exists computer_science;

create table if not exists computer_science.users
(
    user_id   uuid not null primary key,
    user_name varchar(250)
);
