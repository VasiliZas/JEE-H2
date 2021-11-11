package vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "student")
public class StudentDb extends MyAbstractEntity {

    private Integer idgroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgroup", insertable = false, updatable = false)
    private Group group;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Marks> grade = new LinkedList<>();

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

    public StudentDb addMarks(Marks mark) {
        if (mark != null) {
            grade.add(mark);
        }
        return this;
    }

    @Override
    public String toString() {
        return " Student name = " + getName() + '\'' +
                " , id = " + getId() +
                ", login = " + getLogin() + '\'' +
                ", password =  " + getPassword() + '\'' +
                ", age = " + getAge() +
                ", marks = " + grade + '\'' +
                ", group = " + group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentDb studentDb = (StudentDb) o;
        return Objects.equals(idgroup, studentDb.idgroup) && Objects.equals(group, studentDb.group) && Objects.equals(grade, studentDb.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idgroup, group, grade);
    }
}