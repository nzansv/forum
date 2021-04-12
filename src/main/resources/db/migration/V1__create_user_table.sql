create table users
(
    id       serial       not null
        constraint users_pkey
            primary key,
    username varchar(100) not null,
    password varchar(100) not null
);
