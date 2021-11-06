create table student
(
    id       serial
        constraint student_pk
            primary key,
    name     varchar not null,
    login    varchar not null,
    password varchar not null,
    age      integer not null
) using ???;

alter table student owner to vasili;

