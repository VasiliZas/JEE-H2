package vasilizas.myservice.person;

import vasilizas.bean.Student;
import vasilizas.exception.MyWebAppException;
import vasilizas.myservice.security.PersonSecurity;

import static vasilizas.repository.StudentRepository.studentList;

public class StudentService {

    private static StudentService instance;

    private StudentService() {
        // blank default constructor for utility class
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudentMarks(String name, String theme, int mark, int id) {
        if (PersonSecurity.getInstance().checkStudent(studentList, id, name)) {
            studentList.stream()
                    .filter(student -> student.getId() == id)
                    .filter(student -> student.getName().equals(name))
                    .map(Student::getMarks)
                    .forEach(integers -> integers.put(theme, mark));
        } else {
            throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
        }
    }

    public void removeStudentMarks(String name, String theme, int id) {
        if (PersonSecurity.getInstance().checkStudent(studentList, id, name)) {
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

