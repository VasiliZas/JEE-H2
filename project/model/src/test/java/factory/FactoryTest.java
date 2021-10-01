package factory;

import bean.Student;
import org.junit.Assert;
import org.junit.Test;

public class FactoryTest {

    @Test
    public void getTypePerson() {
        var person =  Factory.creatStudent("Student",22);


    }
}