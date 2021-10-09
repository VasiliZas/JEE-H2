package vasilizas.bean;


import java.math.BigDecimal;
import java.util.Objects;

public final class Teacher extends Person {
    private BigDecimal salary;

    public Teacher(String name, int age, String login, String password) {
        super(name, age, login, password);
        this.salary = BigDecimal.valueOf(0);
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
