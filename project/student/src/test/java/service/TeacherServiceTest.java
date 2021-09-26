package service;

import bean.Teacher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static service.ListOfTeacher.teacherList;

public class TeacherServiceTest {
    TeacherService teacherService;
    List<Teacher> testList;

    @Before
    public void setUp() {
        this.teacherService = new TeacherService();
        this.testList = teacherList;
    }

    @Test
    public void teacherAverage() {
        double expected = 933.333;
        assertEquals(expected, teacherService.teacherAverage(testList), 0.001);
    }

    @Test
    public void teacherMax() {
        int expected = 1500;
        assertEquals(expected, teacherService.teacherMax(testList));
    }

    @Test
    public void teacherMin() {
        int expected = 400;
        assertEquals(expected, teacherService.teacherMin(testList));
    }
}
