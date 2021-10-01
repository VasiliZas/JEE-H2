package factory;

import bean.Person;
import bean.Teacher;

public class TeacherProvider implements Provider{
    @Override
    public Person getPerson(String namePerson, int age) {
        return new Teacher(namePerson, age);
    }
}
