package repository;

import bean.Teacher;

import java.util.List;

import static factory.Factory.creatTeacher;

public class TeacherRepository {

    public static List<Teacher> teacherList = List.of(
            creatTeacher("Picasso", 55),
            creatTeacher("Petrovicv", 75),
            creatTeacher("Koltiga", 45)
    );

    private TeacherRepository() {
    }
}
