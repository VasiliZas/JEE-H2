create table salary
(
    id     serial,
    salary numeric not null,
    sid    integer
        constraint salary_teacher_id_fk_2
            references teacher
            on delete cascade
) using ???;

alter table salary owner to vasili;

create unique index salary_id_uindex
    on salary using ??? (id);

