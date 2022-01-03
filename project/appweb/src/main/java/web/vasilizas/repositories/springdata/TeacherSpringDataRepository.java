package web.vasilizas.repositories.springdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.TeacherDb;

@Repository
public interface TeacherSpringDataRepository extends CrudRepository<TeacherDb, Integer> {
}
