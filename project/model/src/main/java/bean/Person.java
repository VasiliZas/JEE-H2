package bean;

import java.util.*;

public abstract class Person {

    protected String name;
    protected int age;
    protected Map<String, String> loginAndPassword;


    protected Person() {
    }

    protected Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.loginAndPassword = new HashMap<String, String>();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(loginAndPassword, person.loginAndPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, loginAndPassword);
    }
}


