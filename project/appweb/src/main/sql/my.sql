-- DROP SCHEMA my;

CREATE SCHEMA my AUTHORIZATION vasili;

CREATE TABLE my.grade
(
    id       serial4 NOT NULL,
    theme    varchar NOT NULL,
    grade    int4    NOT NULL,
    stuid    int4    NULL,
    "groups" varchar NULL,
    CONSTRAINT grade_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX grade_id_uindex ON my.grade USING btree (id);

-- my.grade foreign keys

ALTER TABLE my.grade
    ADD CONSTRAINT grade_student_id_fk FOREIGN KEY (stuid) REFERENCES my.student (id) ON DELETE CASCADE;

CREATE TABLE my."group"
(
    id     serial4 NOT NULL,
    "name" varchar NOT NULL,
    CONSTRAINT group_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX group_id_uindex ON my."group" USING btree (id);
CREATE UNIQUE INDEX group_name_uindex ON my."group" USING btree (name);

-- my."group" foreign keys

ALTER TABLE my."group"
    ADD CONSTRAINT group_teacher_id_fk FOREIGN KEY (id) REFERENCES my.teacher (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE my.salary
(
    id     serial4 NOT NULL,
    salary numeric NOT NULL,
    sid    int4    NULL
);
CREATE UNIQUE INDEX salary_id_uindex ON my.salary USING btree (id);

-- my.salary foreign keys

ALTER TABLE my.salary
    ADD CONSTRAINT salary_teacher_id_fk_2 FOREIGN KEY (sid) REFERENCES my.teacher (id) ON DELETE CASCADE;


CREATE TABLE my.student
(
    id         serial4 NOT NULL,
    "name"     varchar NOT NULL,
    login      varchar NOT NULL,
    "password" varchar NOT NULL,
    age        int4    NOT NULL,
    CONSTRAINT student_pk PRIMARY KEY (id)
);

CREATE TABLE my.studgr
(
    student_id int4 NULL,
    group_id   int4 NULL
);

CREATE TABLE my.teacher
(
    id         serial4 NOT NULL,
    "name"     varchar NOT NULL,
    login      varchar NOT NULL,
    "password" varchar NOT NULL,
    age        int4    NOT NULL,
    CONSTRAINT teacher_pk PRIMARY KEY (id)
);

CREATE TABLE my.themes
(
    id         serial4 NOT NULL,
    themegroup varchar NULL,
    idgroup    int4    NULL,
    CONSTRAINT themes_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX themes_id_uindex ON my.themes USING btree (id);

-- my.themes foreign keys

ALTER TABLE my.themes
    ADD CONSTRAINT themes_group_id_fk FOREIGN KEY (idgroup) REFERENCES my."group" (id);
