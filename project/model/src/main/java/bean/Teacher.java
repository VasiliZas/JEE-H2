package bean;


public class Teacher extends Person {
    private long salary;

    public Teacher(String name, int age) {
        super(name, age);
        this.salary = 0;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
