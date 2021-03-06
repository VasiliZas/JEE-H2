package vasilizas.bean.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "group")
public class Group implements Serializable {

    @OneToOne(mappedBy = "group")
    @Fetch(value = FetchMode.SELECT)
    private TeacherDb teacherDb;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<StudentDb> students = new HashSet<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Themes> them = new HashSet<>();

    @Override
    public String toString() {
        return "name = " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students);
    }
}