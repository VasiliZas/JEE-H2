package service.facade;

import bean.Student;

import static repository.StudentRepository.studentList;
import static service.facade.Service.log;

public class StudentService {
    private StudentService() {
    }

    public static void getStudentInfo(String name) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .forEach(student -> Service.log.info("{} ", student));
    }

    public static void getStudentMarksInformation(String name) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integerList -> log.info("Student {}  marks {}", name, integerList));
    }

    public static void addStudentMarks(String name, int mark) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integers -> integers.add(mark));
    }
}
