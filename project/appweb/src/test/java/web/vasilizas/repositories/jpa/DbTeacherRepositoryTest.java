package web.vasilizas.repositories.jpa;

import org.junit.Test;
import vasilizas.bean.db.TeacherDb;

public class DbTeacherRepositoryTest {

    @Test
    public void addPersonInDb() {
        var user = new TeacherDb().withAge(55)
                .withLogin("kjhg")
                .withPassword("ffff")
                .withName("UserSS");
        DbTeacherRepository.getInstance().addPersonInDb(user);
    }

    @Test
    public void findAll() {
        System.out.println(DbTeacherRepository.getInstance().findAll());
    }
}