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

INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (1, 'Run', 88, 10001, 'Second');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (2, 'Sleep', 54, 10001, 'Second');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (3, 'Run', 69, 10002, 'Second');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (4, 'Sleep', 79, 10002, 'Second');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (5, 'History', 99, 10002, 'First');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (6, 'History', 42, 10001, 'First');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (7, 'Run', 87, 10001, 'First');
INSERT INTO my.grade (id, theme, grade, stuid, "groups")
VALUES (8, 'Run', 32, 10002, 'First');

INSERT INTO my.teacher (id, "name", login, "password", age)
VALUES (20001, 'Fox', 'fox', '123', 46);
INSERT INTO my.teacher (id, "name", login, "password", age)
VALUES (20002, 'Picasso', 'picasso', '456', 56);

INSERT INTO my."group" (id, "name")
VALUES (20002, 'First');
INSERT INTO my."group" (id, "name")
VALUES (20001, 'Second');

INSERT INTO my.themes (id, themegroup, idgroup)
VALUES (1, 'Sleep', 20002);
INSERT INTO my.themes (id, themegroup, idgroup)
VALUES (2, 'History', 20001);
INSERT INTO my.themes (id, themegroup, idgroup)
VALUES (3, 'Run', 20001);
INSERT INTO my.themes (id, themegroup, idgroup)
VALUES (4, 'Run', 20002);

INSERT INTO my.salary (id, salary, sid)
VALUES (1, 2000, 20001);
INSERT INTO my.salary (id, salary, sid)
VALUES (2, 2555, 20001);
INSERT INTO my.salary (id, salary, sid)
VALUES (3, 2480, 20001);
INSERT INTO my.salary (id, salary, sid)
VALUES (4, 1980, 20002);
INSERT INTO my.salary (id, salary, sid)
VALUES (5, 2102, 20002);
INSERT INTO my.salary (id, salary, sid)
VALUES (6, 2460, 20002);

INSERT INTO my.student (id, "name", login, "password", age)
VALUES (10001, 'Bakke', 'bakke', '456', 25);
INSERT INTO my.student (id, "name", login, "password", age)
VALUES (10002, 'Jasmin', 'jasmin', '123', 22);

INSERT INTO my.studgr (student_id, group_id)
VALUES (10001, 20001);
INSERT INTO my.studgr (student_id, group_id)
VALUES (10001, 20002);
INSERT INTO my.studgr (student_id, group_id)
VALUES (10002, 20001);
INSERT INTO my.studgr (student_id, group_id)
VALUES (10002, 20002);
