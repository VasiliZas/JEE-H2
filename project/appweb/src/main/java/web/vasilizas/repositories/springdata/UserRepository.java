package web.vasilizas.repositories.springdata;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import vasilizas.bean.db.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
