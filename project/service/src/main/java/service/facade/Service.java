package service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static factory.Factory.createStudent;
import static factory.Factory.createTeacher;
import static repository.StudentRepository.studentList;
import static repository.TeacherRepository.teacherList;

public class Service {

    public static final Logger log = LoggerFactory.getLogger("Service");

    private Service() {
    }

    public static void createAndAddPerson(String typePerson, String namePerson, int agePerson) {
        switch (typePerson) {
            case "Student" -> studentList.add(createStudent(namePerson, agePerson));
            case "Teacher" -> teacherList.add(createTeacher(namePerson, agePerson));
            default -> log.warn("Incorrect variable entered");
        }
    }
}
