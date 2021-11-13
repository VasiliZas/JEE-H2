package web.vasilizas;

import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class Start {
    public static void main(String[] args) {

        TeacherDb group;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            group = em.find(TeacherDb.class, 20001);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        System.out.println(group);
    }
}
