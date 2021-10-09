package bean;


import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.valueOf;

public final class Teacher extends Person {
    private BigDecimal salary;

    public Teacher(String name, int age) {
        super(name, age);
        this.salary = valueOf(0);
    }

    public Teacher() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(salary, teacher.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
