package vasilizas.bean.db;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentDb extends MyAbstractEntity {

    private static int counter = 10010;

    private String name;
    private String login;
    private String password;
    private int age;
    private Map<String, Integer> marks = new LinkedHashMap<>();

    public StudentDb withId(int id) {
        setId(id);
        return this;
    }

    public StudentDb withAge(Integer age) {
        setAge(age);
        return this;
    }

    public StudentDb setStudentId() {
        setId(counter++);
        return this;
    }

    public StudentDb withName(String name) {
        setName(name);
        return this;
    }

    public StudentDb withLogin(String login) {
        setLogin(login);
        return this;
    }

    public StudentDb withPassword(String password) {
        setPassword(password);
        return this;
    }

    public StudentDb addGrade(String theme, Integer grades) {
        if (marks != null) {
            marks.put(theme, grades);
        }
        return this;
    }
}
