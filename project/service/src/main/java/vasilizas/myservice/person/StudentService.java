package vasilizas.myservice.person;

import vasilizas.bean.Student;
import vasilizas.exception.MyWebAppException;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.myservice.security.PersonSecurity.personSecurity;
import static vasilizas.repository.StudentRepository.studentList;

public class StudentService {

    public static final StudentService studentService = new StudentService();

    private StudentService() {
        // blank default constructor for utility class
    }

    public void getStudentInfo(String name) {
        studentList.stream()
                .filter(student -> student.getName().equals(name))
                .forEach(student -> log.info("{} ", student));
    }

    public void getStudentMarksInformation(String name) {
        studentList.stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getMarks)
                .forEach(stringIntegerMap -> log.info("Student {}  marks {}", name, stringIntegerMap));
    }

    public void addStudentMarks(String name, String theme, int mark, int id) {
        if (personSecurity.checkStudent(studentList, id, name)) {
            studentList.stream()
                    .filter(student -> student.getId() == id)
                    .filter(student -> student.getName().equals(name))
                    .map(Student::getMarks)
                    .forEach(integers -> integers.put(theme, mark));
        } else {
            throw new NullPointerException();
        }
    }

    public void removeStudentMarks(String name, String theme, int id) {
        if (personSecurity.checkStudent(studentList, id, name)) {
            studentList.stream()
                    .filter(student -> student.getId() == id)
                    .filter(student -> student.getName().equals(name))
                    .map(Student::getMarks)
                    .forEach(integers -> integers.remove(theme));
        } else {
            throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
        }
    }
}

