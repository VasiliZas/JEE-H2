package web.vasilizas;

import static vasilizas.repository.TeacherDbRepository.teacherDbList;

public class Start {
    public static void main(String[] args) {

        System.out.println(teacherDbList.get(0).getGroup().getStudents());

//        StudentDb group;
//        try {
//            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
//            EntityTransaction tx = em.getTransaction();
//            tx.begin();
//            group = em.find(StudentDb.class, 10001);
//            tx.commit();
//            em.close();
//        } catch (MyWebAppException | PersistenceException exception) {
//            throw new MyWebAppException(exception.getMessage());
//        }
//        System.out.println(group);
    }
}
