package vasilizas.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Student extends Person {

    private static int counter = 10000;
    private Map<String, Integer> marks;

    public Student(String name, int age, String login, String password) {
        super(name, age, login, password);
        this.marks = new HashMap<>();
        id = counter++;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", marks=" + marks +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marks, id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }
}
