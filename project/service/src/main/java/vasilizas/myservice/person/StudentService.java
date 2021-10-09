package vasilizas.myservice.person;

import vasilizas.bean.Student;

import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.myservice.person.MyService.log;

public class StudentService {
    private StudentService() {
        // blank default constructor for utility class
    }

    public static void getStudentInfo(String name) {
        studentList.stream()
                .filter(student -> student.getName().equals(name))
                .forEach(student -> log.info("{} ", student));
    }

    public static void getStudentMarksInformation(String name) {
        studentList.stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integerList -> log.info("Student {}  marks {}", name, integerList));
    }

    public static void addStudentMarks(String name, int mark) {
        studentList.stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(integers -> integers.add(mark));
    }
}
