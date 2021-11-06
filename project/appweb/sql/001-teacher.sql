create table teacher
(
    id       serial
        constraint teacher_pk
            primary key,
    name     varchar not null,
    login    varchar not null,
    password varchar not null,
    age      integer not null
) using ???;

alter table teacher owner to vasili;

