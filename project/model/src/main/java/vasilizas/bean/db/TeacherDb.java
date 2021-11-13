package vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "teacher")
public class TeacherDb extends MyAbstractEntity {

    @OneToOne
    @JoinColumn(name = "id")
    private Group group;

    @OneToMany(mappedBy = "teacher", targetEntity = Salary.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Salary> salary = new LinkedList<>();

    public TeacherDb withId(int id) {
        setId(id);
        return this;
    }

    public TeacherDb withAge(Integer age) {
        setAge(age);
        return this;
    }

    public TeacherDb withName(String name) {
        setName(name);
        return this;
    }

    public TeacherDb withLogin(String login) {
        setLogin(login);
        return this;
    }

    public TeacherDb withPassword(String password) {
        setPassword(password);
        return this;
    }

    public TeacherDb addSalary(Salary salarys) {
        if (salary != null) {
            salary.add(salarys);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TeacherDb teacherDb = (TeacherDb) o;
        return Objects.equals(group, teacherDb.group) && Objects.equals(salary, teacherDb.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, salary);
    }

    @Override
    public String toString() {
        return "Teacher name = " + getName() +
                ", id = " + getId() +
                ", login = " + getLogin() +
                ", password = " + getPassword() +
                ", age = " + getAge() +
                ", salary = " + salary +
                ", group = " + group;
    }
}
