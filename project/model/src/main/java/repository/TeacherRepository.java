package repository;

import bean.Teacher;

import java.util.List;

import static factory.Factory.createTeacher;

public final class TeacherRepository {

    public static List<Teacher> teacherList = List.of(
            createTeacher("Picasso", 55),
            createTeacher("Petrovicv", 75),
            createTeacher("Koltiga", 45)
    );

    private TeacherRepository() {
    }
}
