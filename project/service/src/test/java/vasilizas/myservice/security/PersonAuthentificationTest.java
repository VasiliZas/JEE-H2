package vasilizas.myservice.security;

import org.junit.Test;
import vasilizas.myservice.person.MyService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static vasilizas.repository.StudentRepository.studentList;

public class PersonAuthentificationTest {

    @Test
    public void check() {
        MyService.getInstance().createAndAddPerson("Student", "Bakke", 25, "myLogin", "rty");
        assertTrue(PersonAuthentication.getInstance().check("Bakke", "myLogin", "rty", studentList));
    }

    @Test
    public void getInstance() {
        assertNotNull(PersonAuthentication.getInstance());
    }
}
