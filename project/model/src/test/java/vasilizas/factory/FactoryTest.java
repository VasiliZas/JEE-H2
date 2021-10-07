package vasilizas.factory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FactoryTest {

    @Test
    public void createStudent() {
        var studentTest = Factory.createStudent("Bakke", 28);
        assertNotNull(studentTest);
        assertEquals(28, studentTest.getAge());
    }

    @Test
    public void createTeacher() {
        var teacherTest = Factory.createStudent("Jasmin", 21);
        assertNotNull(teacherTest);
        assertEquals("Jasmin", teacherTest.getName());
    }

    @Test
    public void getTypePerson() {
        var personTest = Factory.getTypePerson("Student", "Bakke", 18);
        assertNotNull(personTest);
        assertEquals(18, personTest.getAge());
    }
}