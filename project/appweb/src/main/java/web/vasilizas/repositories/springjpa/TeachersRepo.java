package web.vasilizas.repositories.springjpa;

import org.springframework.data.repository.CrudRepository;
import vasilizas.bean.db.TeacherDb;

public interface TeachersRepo extends CrudRepository<TeacherDb, Integer> {
}
