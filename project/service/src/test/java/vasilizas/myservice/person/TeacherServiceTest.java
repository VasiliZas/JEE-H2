package vasilizas.myservice.person;

import org.junit.Test;
import vasilizas.exception.MyWebAppException;

import static org.junit.Assert.assertNotNull;

public class TeacherServiceTest {

    @Test
    public void getInstanceTeacher() {
        assertNotNull(TeacherService.getInstance());
    }

    @Test(expected = MyWebAppException.class)
    public void setTeacherSalary() {
        TeacherService.getInstance().setTeacherSalary("Jasmin", 20000, 5000);
    }

    @Test(expected = NullPointerException.class)
    public void averageSalary() {
        TeacherService.getInstance().averageSalary("Jasmin", 5);
    }
}