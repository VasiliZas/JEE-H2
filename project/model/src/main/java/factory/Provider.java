package factory;

import bean.Person;
import bean.Student;

public interface Provider {

    Person getPerson(String namePerson, int age);
}
