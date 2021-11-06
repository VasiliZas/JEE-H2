create table my.salary
(
    id     serial,
    salary numeric not null,
    sid    integer
        constraint salary_teacher_id_fk_2
            references my.teacher
            on delete cascade
) using ???;

alter table my.salary owner to vasili;

create unique index salary_id_uindex
    on my.salary using ??? (id);

