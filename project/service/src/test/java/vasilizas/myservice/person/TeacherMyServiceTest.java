package vasilizas.myservice.person;

import org.junit.Before;
import org.junit.Test;

public class TeacherMyServiceTest {

    @Before
    public void setUp() {
        MyService.createAndAddPerson("Teacher", "Jasmin", 35, "login", "oio");
        TeacherService.setTeacherSalary("Jasmin", "login", 5000.50);
    }

    @Test
    public void getTeacherSalary() {
        TeacherService.getTeacherSalaryInfo("Jasmin");
    }

    @Test
    public void getTeacherInfo() {
        MyService.createAndAddPerson("Teacher", "Test", 55, "login", "hhh");
        TeacherService.setTeacherSalary("Test", "login", 1000.77);
        TeacherService.getTeacherInfo("Test");
        TeacherService.getTeacherInfo("Jasmin");
    }

    @Test
    public void setTeacherSalary() {
        TeacherService.setTeacherSalary("Jasmin", "login", 6000);
        TeacherService.getTeacherInfo("Jasmin");
    }
}
