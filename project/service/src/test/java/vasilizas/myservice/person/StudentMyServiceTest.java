package vasilizas.myservice.person;

import vasilizas.bean.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static vasilizas.repository.StudentRepository.studentList;

public class StudentMyServiceTest {

    @Before
    public void setUp()  {
        MyService.createAndAddPerson("Student","Bakke", 22, "kl", "ppp");
    }

    @Test
    public void getStudentInfo() {
        StudentService.getStudentInfo("Bakke");
    }

    @Test
    public void addStudentMarks() {
        StudentService.addStudentMarks("Bakke", 95);
        StudentService.getStudentInfo("Bakke");
    }

    @Test
    public void getStudentMarks() {
        StudentService.addStudentMarks("Bakke", 97);
        StudentService.addStudentMarks("Bakke", 88);
        StudentService.addStudentMarks("Bakke", 85);
        StudentService.getStudentMarksInformation("Bakke");
        var mark = getMarks(studentList, "Bakke", 2);
        Assert.assertEquals(85, mark);
    }

    private int getMarks(List<Student> list, String name, int index) {
        int mark = 0;
        for (Student student : list) {
            if (student.getName().equals(name)) {
                mark = student.getMarks().get(index);
            }
        }
        return mark;
    }
}
