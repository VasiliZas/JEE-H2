package vasilizas.bean.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyAbstractEntity {
    private Integer id;

    public MyAbstractEntity withId(Integer id) {
        setId(id);
        return this;
    }
}
