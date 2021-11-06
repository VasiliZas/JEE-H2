package web.vasilizas.repositories.factory;

import web.vasilizas.repositories.Repository;
import web.vasilizas.repositories.sql.SqlStudentRepository;
import web.vasilizas.repositories.sql.SqlTeacherRepository;

public class SqlFactory {
    private SqlFactory() {
    }

    public static Repository getTypePersonRepository(String typeUser) {
        return switch (typeUser) {
            case "Student" -> SqlStudentRepository.getInstance();
            case "Teacher" -> SqlTeacherRepository.getInstance();
            default -> null;
        };
    }
}
