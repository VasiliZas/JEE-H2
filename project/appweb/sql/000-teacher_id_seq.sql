create sequence my.teacher_id_seq
    as integer;

alter sequence my.teacher_id_seq owner to vasili;

alter sequence my.teacher_id_seq owned by my.teacher.id;

