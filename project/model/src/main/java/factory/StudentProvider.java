package factory;

import bean.Student;

public class StudentProvider implements Provider{
    @Override
    public Student getPerson(String namePerson, int age) {
        return new Student(namePerson, age);
    }
}
