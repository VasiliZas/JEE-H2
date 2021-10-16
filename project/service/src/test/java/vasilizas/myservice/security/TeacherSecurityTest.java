package vasilizas.myservice.security;

import org.junit.Test;
import vasilizas.myservice.person.MyService;

import static org.junit.Assert.assertTrue;

public class TeacherSecurityTest {

    @Test
    public void check() {
        MyService.createAndAddPerson("Teacher", "Bakke", 22, "login", "123456");
        assertTrue(TeacherSecurity.check("Bakke", "login", "123456"));
    }
}
