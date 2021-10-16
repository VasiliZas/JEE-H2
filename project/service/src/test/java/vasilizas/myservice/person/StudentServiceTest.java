package vasilizas.myservice.person;

import org.junit.Test;
import vasilizas.exception.MyWebAppException;

import static org.junit.Assert.assertNotNull;
import static vasilizas.myservice.person.StudentService.getInstance;

public class StudentServiceTest {

    @Test(expected = MyWebAppException.class)
    public void addStudentMarks() {
        getInstance().addStudentMarks("Bakke", "Running", 88, 10000);
    }

    @Test(expected = MyWebAppException.class)
    public void removeStudentMarks() {
        getInstance().removeStudentMarks("Bakke", "Running", 10001);
    }

    @Test
    public void getInstanceStudent() {
        assertNotNull(getInstance());
    }
}