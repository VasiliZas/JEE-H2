package vasilizas.myservice.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static vasilizas.myservice.person.MyService.getInstance;
import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.repository.TeacherRepository.teacherList;

public class PersonSecurityTest {

    @Before
    public void setUp() {
        getInstance().createAndAddPerson("Student", "Bakke", 22, "login", "password");
        getInstance().createAndAddPerson("Teacher", "Picasso", 56, "login", "password");
    }

    @Test
    public void getInstancePerson() {
        Assert.assertNotNull(PersonSecurity.getInstance());
    }

    @Test
    public void checkStudent() {
        assertTrue(PersonSecurity.getInstance().checkStudent(studentList, 10000, "Bakke"));
    }

    @Test
    public void checkTeacher() {
        assertTrue(PersonSecurity.getInstance().checkTeacher(teacherList, 20000, "Picasso"));
    }
}
