create table grade
(
    id    serial,
    theme varchar not null,
    grade integer not null,
    stuid integer
        constraint grade_student_id_fk
            references student
            on delete cascade
) using ???;

alter table grade owner to vasili;

create unique index grade_id_uindex
    on grade using ??? (id);

