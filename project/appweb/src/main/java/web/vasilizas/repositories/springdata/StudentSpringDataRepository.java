package web.vasilizas.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.StudentDb;

@Repository
public interface StudentSpringDataRepository extends JpaRepository<StudentDb, Integer> {
}
