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
public class StudentDb extends AbstractEntity {

    private String name;
    private String login;
    private String password;
    private int age;
    private Map<String, Integer> grade = new LinkedHashMap<>();

    public StudentDb withId(int id) {
        setId(id);
        return this;
    }

    public StudentDb withAge(Integer age) {
        setAge(age);
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
        if (grade != null) {
            grade.put(theme, grades);
        }
        return this;
    }
}
