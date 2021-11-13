package web.vasilizas;

import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class Start {
    public static void main(String[] args) {

        StudentDb group;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            group = em.find(StudentDb.class, 10001);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        System.out.println(group);
    }
}
