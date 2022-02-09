package web.vasilizas.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.TeacherDb;

@Repository
public interface TeacherSpringDataRepository extends JpaRepository<TeacherDb, Integer> {
}
