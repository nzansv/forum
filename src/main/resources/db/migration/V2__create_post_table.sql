create table posts
(
    id       serial       not null
        constraint posts_pkey
            primary key,
    content varchar not null,
    like_counter int not null,
    user_id      integer
        constraint fk_user
            references users
);
