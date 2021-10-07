package vasilizas.myservice.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static vasilizas.factory.Factory.createStudent;
import static vasilizas.factory.Factory.createTeacher;
import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.repository.TeacherRepository.teacherList;

public class MyService {

    public static final Logger log = LoggerFactory.getLogger("Service");

    private MyService() {
    }

    public static void createAndAddPerson(String typePerson, String namePerson, int agePerson) {

        switch (typePerson) {
            case "Student" -> studentList.add(createStudent(namePerson, agePerson));
            case "Teacher" -> teacherList.add(createTeacher(namePerson, agePerson));
            default -> log.warn("Incorrect variable entered");
        }
    }
}

