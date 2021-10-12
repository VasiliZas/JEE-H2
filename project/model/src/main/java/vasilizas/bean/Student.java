package vasilizas.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Student extends Person {
    private List<Integer> marks;

    public Student(String name, int age, String login, String password) {
        super(name, age, login, password);
        this.marks = new ArrayList<>();
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marks);
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }
}
