package service.facade;

import bean.Student;

import static repository.StudentRepository.studentList;
import static service.facade.Service.log;

public class StudentService {
    public StudentService() {
    }

    public void getStudentInfo(String name) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .forEach(student -> Service.log.info("{} ", student));
    }

    public void getStudentMarks(String name) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integers -> log.info("Student {}  marks {}", name, integers));
    }

    public void addStudentMarks(String name, int mark) {
        studentList.stream().filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integers -> integers.add(mark));
    }
}
