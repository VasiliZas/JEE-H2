package service.facade;

import org.junit.Before;
import org.junit.Test;

public class TeacherServiceTest {

    @Before
    public void setUp() {
        Service.createAndAddPerson("Teacher", "Jasmin", 35);
        TeacherService.setTeacherSalary("Jasmin", 5000.50);
    }

    @Test
    public void getTeacherSalary() {
        TeacherService.getTeacherSalaryInfo("Jasmin");
    }

    @Test
    public void getTeacherInfo() {
        Service.createAndAddPerson("Teacher", "Test", 55);
        TeacherService.setTeacherSalary("Test", 1000.77);
        TeacherService.getTeacherInfo("Test");
        TeacherService.getTeacherInfo("Jasmin");
    }

    @Test
    public void setTeacherSalary() {
        TeacherService.setTeacherSalary("Jasmin", 6000);
        TeacherService.getTeacherInfo("Jasmin");
    }
}