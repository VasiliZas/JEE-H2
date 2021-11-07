package vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal salary;
    private int sid;
    @ManyToOne(targetEntity = TeacherDb.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sid", insertable = false, updatable = false)
    private TeacherDb teacher;

    public Integer getTid() {
        return sid;
    }

    public void setSid(Integer salaryid) {
        this.sid = salaryid;
    }

    public Salary withSalary(BigDecimal salary) {
        setSalary(salary);
        return this;
    }

    public Salary withTid(Integer salaryid) {
        setSid(salaryid);
        return this;
    }

    public Salary withId(Integer id) {
        setId(id);
        return this;
    }

    @Override
    public String toString() {
        return " " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary1 = (Salary) o;
        return id == salary1.id && sid == salary1.sid && Objects.equals(salary, salary1.salary) && Objects.equals(teacher, salary1.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salary, sid, teacher);
    }
}
