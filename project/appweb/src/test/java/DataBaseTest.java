import org.junit.Before;
import org.junit.Test;
import web.vasilizas.controller.DataBase;

import static org.junit.Assert.assertEquals;
import static vasilizas.repository.StudentDbRepository.studentDbList;

public class DataBaseTest {

    String actual;
    @Before
    public void setUp() {
       DataBase.getInstance().getStudentFromDb("Jasmin");
         actual = studentDbList.get(0).getName();
    }

    @Test
    public void getPersonFromDb() {
        assertEquals("Jasmin", actual);
    }
}