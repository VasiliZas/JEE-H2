package factory;

import org.junit.Test;

public class FactoryTest {

    @Test
    public void getTypePerson() {
        var person =  Factory.createStudent("Student",22);


    }
}