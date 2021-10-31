package web.vasilizas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Hibernet work :");
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            StudentDb studentDb = em.find(StudentDb.class, 10003);
            em.remove(studentDb);
            TypedQuery<StudentDb> fromStudentDb = em.createQuery("from StudentDb ", StudentDb.class);
            List<StudentDb> dbList = fromStudentDb.getResultList();
            dbList.forEach(e -> log.info(" !!!!!!  {}", e));
            tx.commit();
            em.close();
        } catch (MyWebAppException exception) {
            log.error("Exception : ", exception);
        }
    }

}
