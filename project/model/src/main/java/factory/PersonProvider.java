package factory;

import bean.Person;

public class PersonProvider implements Provider {
    @Override
    public Person getPerson(String namePerson, int age) {
        return new Person() {
        };
    }
}
