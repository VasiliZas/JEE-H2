create sequence my.salary_id_seq
    as integer;

alter sequence my.salary_id_seq owner to vasili;

alter sequence my.salary_id_seq owned by my.salary.id;

