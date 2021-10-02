package service.facade;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static repository.StudentRepository.studentList;

public class ServiceTest {

    @Test
    public void createAndAddPerson() {
        Service.createAndAddPerson("Student", "StudentTest", 55);
        var leght1 = studentList.size();
        Service.createAndAddPerson("Student", "StudentTest2", 35);
        var leght2 = studentList.size();
        assertNotEquals(leght1, leght2);
        assertEquals(2,leght2);
    }
}