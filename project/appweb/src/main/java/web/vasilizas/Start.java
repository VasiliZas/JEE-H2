package web.vasilizas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.jpa.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Hibernet work :");
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
//            var student = new StudentDb().withAge(562)
//                    .withLogin("Gaws")
//                    .withName("Nam2")
//                    .withPassword("qwerty")
//                    .setStudentId();

            var grade = new Marks().withGrade(88).withTheme("Eating").withId(10000);

            //StudentDb studentDb = em.find(StudentDb.class, 10003);
            em.persist(grade);

            // em.remove(studentDb);
            TypedQuery<StudentDb> fromStudentDb = em.createQuery("from StudentDb ", StudentDb.class);
            TypedQuery<Marks> fromGrade = em.createQuery("from Marks ", Marks.class);
            List<StudentDb> dbList = fromStudentDb.getResultList();
            List<Marks> dbGrade = fromGrade.getResultList();
            dbGrade.forEach(g -> log.info(" !!!!!!  {}", g));
            dbList.forEach(e -> log.info(" !!!!!!  {}", e));
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            log.error("Exception : ", exception);
        }
    }

}
