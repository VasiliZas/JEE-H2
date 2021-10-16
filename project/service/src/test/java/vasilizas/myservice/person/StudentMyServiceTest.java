package vasilizas.myservice.person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vasilizas.bean.Student;

import java.util.List;

import static vasilizas.myservice.person.StudentService.getStudentMarksInformation;
import static vasilizas.repository.StudentRepository.studentList;

public class StudentMyServiceTest {

    int marks;

    @Before
    public void setUp() {
        MyService.createAndAddPerson("Student", "Bakke", 22, "kl", "ppp");
        MyService.createAndAddPerson("Student", "Jasmin", 25, "klo", "poi");
    }

    @Test
    public void getStudentInfo() {
        StudentService.getStudentInfo("Bakke");
    }

    @Test
    public void addStudentMarks() {
        StudentService.addStudentMarks("Jasmin", "Running", 95, 0);
        StudentService.addStudentMarks("Jasmin", "Sleeping", 99, 0);
        Assert.assertEquals(99, getMarks(studentList,"Jasmin","Sleeping"));
        StudentService.getStudentInfo("Bakke");
    }

    @Test
    public void getStudentMarks() {
        StudentService.addStudentMarks("Bakke", "Running", 97, 0);
        StudentService.addStudentMarks("Bakke", "Sleeping", 88, 0);
        StudentService.addStudentMarks("Bakke", "Running", 85, 0);
        getStudentMarksInformation("Bakke");
        var mark = getMarks(studentList, "Bakke", "Running");
        Assert.assertEquals(85, mark);
    }

    private int getMarks(List<Student> list, String name, String theme) {

        for (Student student : list) {
            if (student.getName().equals(name)) {
                marks = student.getMarks().get(theme);
            }
        }
        return marks;
    }
}
