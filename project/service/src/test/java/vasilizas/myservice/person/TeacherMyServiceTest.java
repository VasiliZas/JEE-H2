package vasilizas.myservice.person;

import org.junit.Before;
import org.junit.Test;

import static vasilizas.myservice.person.MyService.myService;
import static vasilizas.myservice.person.TeacherService.teacherService;

public class TeacherMyServiceTest {

    @Before
    public void setUp() {
        myService.createAndAddPerson("Teacher", "Jasmin", 35, "login", "oio");
        teacherService.setTeacherSalary("Jasmin", 20000, 5000.50);
    }

    @Test
    public void getTeacherSalary() {
        teacherService.getTeacherSalaryInfo("Jasmin");
    }

    @Test
    public void setTeacherSalary() {
        teacherService.setTeacherSalary("Jasmin", 20000, 6000);
        teacherService.getTeacherInfo("Jasmin");
    }
}
