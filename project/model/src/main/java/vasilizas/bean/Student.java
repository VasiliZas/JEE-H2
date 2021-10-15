package vasilizas.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Student extends Person {
    private List<Integer> marks;
    private int id;

    public Student(String name, int age, String login, String password) {
        super(name, age, login, password);
        this.marks = new ArrayList<>();
        this.id = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }
}
