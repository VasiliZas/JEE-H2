package vasilizas.myservice.security;

import org.junit.Assert;
import org.junit.Test;
import vasilizas.myservice.person.MyService;

import static org.junit.Assert.assertTrue;
import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.repository.TeacherRepository.teacherList;

public class PersonSecurityTest {

    @Test
    public void getInstancePerson() {
        Assert.assertNotNull(PersonSecurity.getInstance());
    }

//    @Test
//    public void checkStudent() {
//        MyService.getInstance().createAndAddPerson("Student", "Bakke", 22, "login", "password");
//        assertTrue(PersonSecurity.getInstance().checkStudent(studentList, 10000, "Bakke"));
//    }
//
//    @Test
//    public void checkTeacher() {
//        MyService.getInstance().createAndAddPerson("Teacher", "Picasso", 56, "login", "password");
//        assertTrue(PersonSecurity.getInstance().checkTeacher(teacherList, 20000, "Picasso"));
//    }
}
