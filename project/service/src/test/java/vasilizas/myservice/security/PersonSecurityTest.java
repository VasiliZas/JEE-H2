package vasilizas.myservice.security;

import org.junit.Before;
import org.junit.Test;

import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static vasilizas.myservice.security.PersonSecurity.addLoginAndPassword;
import static vasilizas.myservice.security.StudentSecurity.getPassword;

public class PersonSecurityTest {


    @Before
    public void setUp() {
        createAndAddPerson("Student", "Bakke", 22);
        createAndAddPerson("Student", "Jasmin", 25);
        createAndAddPerson("Teacher", "Picasso", 56);
        createAndAddPerson("Teacher", "Fox", 66);
    }

    @Test
    public void addLoginAndPasswordStudent() {
        addLoginAndPassword("Student", "Bakke", "dog", "4563289");
        getPassword("Bakke", "dog");
    }

    @Test
    public void addLoginAndPasswordTeacher() {
        addLoginAndPassword("Teacher", "Picasso", "car", "5558455");
        TeacherSecurity.getPassword("Picasso", "car");
    }
}