create table if not exists computer_science.authors (
    author_id uuid primary key not null,
    author_first_name varchar(250),
    author_second_name varchar(250) not null
);

create table if not exists computer_science.books (
    book_id uuid primary key not null ,
    book_name varchar(250) not null,
    book_release_date date not null ,
    book_author_id uuid not null,
    foreign key (book_author_id) references computer_science.authors(author_id)
);