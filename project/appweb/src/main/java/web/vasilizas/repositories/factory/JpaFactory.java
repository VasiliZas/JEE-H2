package web.vasilizas.repositories.factory;

import web.vasilizas.repositories.Repository;
import web.vasilizas.repositories.jpa.DbStudentRepository;
import web.vasilizas.repositories.jpa.DbTeacherRepository;

public class JpaFactory {
    private JpaFactory() {
    }

    public static Repository getTypePersonRepository(String typeUser) {
        return switch (typeUser) {
            case "Student" -> DbStudentRepository.getInstance();
            case "Teacher" -> DbTeacherRepository.getInstance();
            default -> null;
        };
    }
}
