package vasilizas.bean.db;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "themes")
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String themegroup;
    private Integer idgroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgroup", insertable = false, updatable = false)
    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Themes themes = (Themes) o;
        return Objects.equals(id, themes.id) && Objects.equals(themegroup, themes.themegroup)
                && Objects.equals(idgroup, themes.idgroup) && Objects.equals(group, themes.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, themegroup, idgroup, group);
    }

    @Override
    public String toString() {
        return themegroup;
    }
}
