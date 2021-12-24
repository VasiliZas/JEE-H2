package vasilizas.bean.db;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "grade")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String theme;

    @Min(value = 0, message = "Should be between 0 and 100")
    @Max(value = 100, message = "Should be between 0 and 100")
    private Integer grade;
    private Integer stuid;

    @NotEmpty(message = "Should not be empty!")
    @Size(min = 3, max = 25, message = "Should be between 3 and 25 characters")
    private String groups;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stuid", insertable = false, updatable = false)
    private StudentDb student;

    public StudentDb getStudent() {
        return student;
    }

    public void setStudent(StudentDb student) {
        this.student = student;
    }

    public Marks withGrade(Integer grade) {
        setGrade(grade);
        return this;
    }

    public Marks withGroup(String group) {
        setGroups(group);
        return this;
    }

    public Marks withTheme(String theme) {
        setTheme(theme);
        return this;
    }

    public Marks withStudentId(Integer id) {
        setStuid(id);
        return this;
    }

    @Override
    public String toString() {
        return " group = " + groups +
                " theme = " + theme +
                " , grade = " + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marks marks = (Marks) o;
        return Objects.equals(id, marks.id) && Objects.equals(theme, marks.theme) && Objects.equals(grade, marks.grade) && Objects.equals(stuid, marks.stuid) && Objects.equals(groups, marks.groups) && Objects.equals(student, marks.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theme, grade, stuid, groups, student);
    }
}
