package web.vasilizas.repositories.springdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.StudentDb;

@Repository
public interface StudentSpringDataRepository extends CrudRepository<StudentDb, Integer> {
}
