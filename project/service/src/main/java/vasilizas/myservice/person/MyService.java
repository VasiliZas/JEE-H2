package vasilizas.myservice.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static vasilizas.factory.Factory.createStudent;
import static vasilizas.factory.Factory.createTeacher;
import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.repository.TeacherRepository.teacherList;

public class MyService {

    public static final Logger log = LoggerFactory.getLogger("Service");

    public static final MyService myService = new MyService();

    private MyService() {
        // blank default constructor for utility class
    }

    public void createAndAddPerson(String typePerson, String namePerson, int agePerson, String login, String password) {

        switch (typePerson) {
            case "Student" -> studentList.add(createStudent(namePerson, agePerson, login, password));
            case "Teacher" -> teacherList.add(createTeacher(namePerson, agePerson, login, password));
            default -> log.warn("Incorrect variable entered");
        }
    }
}

