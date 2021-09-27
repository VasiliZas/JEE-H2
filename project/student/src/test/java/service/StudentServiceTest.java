package service;

import bean.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static service.ListOfStudent.studentList;

public class StudentServiceTest {
    StudentService studentService;
    List<Student> testList;

    @Before
    public void setUp() {
        this.studentService = new StudentService();
        this.testList = studentList;
    }

    @Test
    public void studentAverage() {
        double expected = 5.833;
        assertEquals(expected, studentService.studentAverage(testList), 0.001);
    }

    @Test
    public void studentMax() {
        int expected = 9;
        assertEquals(expected, studentService.studentMax(testList));
    }

    @Test
    public void studentMin() {
        int expected = 2;
        assertEquals(expected, studentService.studentMin(testList));
    }
}
