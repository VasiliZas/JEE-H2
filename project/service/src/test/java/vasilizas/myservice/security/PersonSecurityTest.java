package vasilizas.myservice.security;

import org.junit.Before;
import org.junit.Test;

import static vasilizas.myservice.person.MyService.myService;
import static vasilizas.myservice.security.PersonSecurity.personSecurity;
import static vasilizas.myservice.security.StudentSecurity.studentSecurity;
import static vasilizas.myservice.security.TeacherSecurity.teacherSecurity;

public class PersonSecurityTest {


    @Before
    public void setUp() {
        myService.createAndAddPerson("Student", "Bakke", 22, "login", "password");
        myService.createAndAddPerson("Student", "Jasmin", 25, "login", "password");
        myService.createAndAddPerson("Teacher", "Picasso", 56, "login", "password");
        myService.createAndAddPerson("Teacher", "Fox", 66, "login", "password");
    }

    @Test
    public void addLoginAndPasswordStudent() {
        studentSecurity.addLogin("Bakke", "myDog");
        studentSecurity.getPassword("Bakke");
    }

    @Test
    public void addLoginAndPasswordTeacher() {
        personSecurity.addLogin("Teacher", "Picasso", "car");
        teacherSecurity.getPassword("Picasso");
    }
}
