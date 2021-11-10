package vasilizas.bean.db;

import vasilizas.bean.Person;

public class StudentDb extends Person {


    public StudentDb(String name, int age, String login, String password, int id) {
        super(name, age, login, password, id);
    }

    @Override
    public String toString() {
        return "StudentDb{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}


