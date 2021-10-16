package vasilizas.myservice.security;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static vasilizas.myservice.person.MyService.myService;
import static vasilizas.myservice.security.TeacherSecurity.teacherSecurity;

public class TeacherSecurityTest {

    @Test
    public void check() {
        myService.createAndAddPerson("Teacher", "Bakke", 22, "login", "123456");
        assertTrue(teacherSecurity.check("Bakke", "login", "123456"));
    }
}
