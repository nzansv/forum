create table comments
(
    id           serial       not null
        constraint comments_pkey
            primary key,
    content      varchar(100) not null,
    like_counter integer,
    post_id      integer
        constraint fk_post
            references posts,
    user_id      integer
        constraint fk_user
            references users
);
