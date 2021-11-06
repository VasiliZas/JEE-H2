create table my.grade
(
    id    serial,
    theme varchar not null,
    grade integer not null,
    stuid integer
        constraint grade_student_id_fk
            references my.student
            on delete cascade
) using ???;

alter table my.grade owner to vasili;

create unique index grade_id_uindex
    on my.grade using ??? (id);

