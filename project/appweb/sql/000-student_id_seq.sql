create sequence my.student_id_seq
    as integer;

alter sequence my.student_id_seq owner to vasili;

alter sequence my.student_id_seq owned by my.student.id;

