create table if not exists computer_science.borrows(
    borrow_id uuid primary key not null ,
    start_date date not null ,
    end_date date not null,
    book_id uuid not null ,
    user_id uuid not null,
    foreign key (book_id) references computer_science.books(book_id),
    foreign key (user_id) references computer_science.users(user_id)
);