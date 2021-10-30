package vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TeacherDb extends MyAbstractEntity {
    private static int counter = 20010;
    private String name;
    private String login;
    private String password;
    private int age;
    private List<BigDecimal> salary = new LinkedList<>();

    public TeacherDb withId(int id) {
        setId(id);
        return this;
    }

    public TeacherDb setTeacherId() {
        setId(counter++);
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

    public TeacherDb addGrade(BigDecimal salarys) {
        if (salary != null) {
            salary.add(salarys);
        }
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "name = " + name +
                ", login = " + login +
                ", password = " + password +
                ", age = " + age +
                ", salary = " + salary +
                " " + " id = " + getId() + "}" + "\n";
    }
}
