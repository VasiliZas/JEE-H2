package vasilizas.bean;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Teacher extends Person {
    private List<BigDecimal> salary;
    private int id;

    public Teacher(String name, int age, String login, String password) {
        super(name, age, login, password);
        this.salary = new ArrayList<>();
        this.id = 0;
    }

    public Teacher() {
    }

    public List<BigDecimal> getSalary() {
        return salary;
    }

    public void setSalary(List<BigDecimal> salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(salary, teacher.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "salary=" + salary +
                ", id=" + id +
                '}';
    }
}

