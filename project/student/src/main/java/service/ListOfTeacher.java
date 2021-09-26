package service;

import bean.Teacher;

import java.util.List;

public class ListOfTeacher {
    public static final List<Teacher> teacherList = List.of(
            new Teacher("Teacher1", 1500),
            new Teacher("Teacher2", 1200),
            new Teacher("Teacher3", 700),
            new Teacher("Teacher4", 400),
            new Teacher("Teacher4", 500),
            new Teacher("Teacher6", 1300));

    private ListOfTeacher() {
    }
}
