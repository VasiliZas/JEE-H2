package vasilizas.bean.db;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class MyAbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String name;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String login;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String password;

    @Min(value = 18, message = "Should be between 18 and 157")
    @Max(value = 157, message = "Should be between 18 and 157")
    private int age;

    public MyAbstractEntity withId(Integer id) {
        setId(id);
        return this;
    }
}
