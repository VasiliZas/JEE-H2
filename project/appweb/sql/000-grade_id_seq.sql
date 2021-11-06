create sequence my.grade_id_seq
    as integer;

alter sequence my.grade_id_seq owner to vasili;

alter sequence my.grade_id_seq owned by my.grade.id;

