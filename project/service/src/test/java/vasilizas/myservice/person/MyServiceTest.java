package vasilizas.myservice.person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static vasilizas.myservice.person.MyService.getInstance;
import static vasilizas.repository.StudentRepository.studentList;

public class MyServiceTest {

    @Test
    public void createAndAddPerson() {
        getInstance().createAndAddPerson("Student", "StudentTest", 55, "login", "password");
        var leght1 = studentList.size();
        getInstance().createAndAddPerson("Student", "StudentTest2", 35, "login", "password");
        var leght2 = studentList.size();
        assertNotEquals(leght1, leght2);
        assertEquals(2, leght2);
    }
}
