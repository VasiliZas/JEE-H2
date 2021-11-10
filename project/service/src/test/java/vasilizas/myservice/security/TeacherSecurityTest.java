package vasilizas.myservice.security;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static vasilizas.myservice.person.MyService.getInstance;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherSecurityTest {

    @Test
    public void check() {
        getInstance().createAndAddPerson("Teacher", "Bakke", 22, "login", "123456");
        assertTrue(PersonAuthentication.getInstance().check("Bakke", "login", "123456", teacherList));
    }
}
