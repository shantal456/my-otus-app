create table if not exists users
(
    id uuid not null primary key,
    first_name varchar(256) not null,
    second_name varchar(256) not null,
    birthdate date,
    biography text,
    city varchar(256),
    password varchar(256) not null,
    created timestamp with time zone,
    updated timestamp with time zone,
    status varchar(256) default 'ACTIVE'
    );
