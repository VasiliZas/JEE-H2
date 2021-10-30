package vasilizas.bean.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyAbstractEntity {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private int age;

    public MyAbstractEntity withId(Integer id) {
        setId(id);
        return this;
    }
}
