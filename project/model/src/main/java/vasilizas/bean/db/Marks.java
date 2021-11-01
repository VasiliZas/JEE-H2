package vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode()
@Data

@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Marks {


    private Integer id;
    private String theme;
    private Integer grade;

    public Marks withGrade(Integer grade) {
        setGrade(grade);
        return this;
    }

    public Marks withTheme(String theme) {
        setTheme(theme);
        return this;
    }

    public Marks withId(Integer id) {
        setId(id);
        return this;
    }
}
