package web.vasilizas.repositories.factory;

import web.vasilizas.repositories.interfaces.StudentRepository;
import web.vasilizas.repositories.interfaces.TeacherRepository;
import web.vasilizas.repositories.jpa.JpaStudentRepository;
import web.vasilizas.repositories.jpa.JpaTeacherRepository;
import web.vasilizas.repositories.memory.MemoryStudentRepository;
import web.vasilizas.repositories.memory.MemoryTeacherRepository;
import web.vasilizas.repositories.sql.SqlStudentRepository;
import web.vasilizas.repositories.sql.SqlTeacherRepository;

public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static StudentRepository getStudentRepository(String typeRepository) {
        return switch (typeRepository) {
            case "JPA" -> JpaStudentRepository.getInstance();
            case "SQL" -> SqlStudentRepository.getInstance();
            case "MEMORY" -> MemoryStudentRepository.getInstance();
            default -> null;
        };
    }

    public static TeacherRepository getTeacherRepository(String typeRepository) {
        return switch (typeRepository) {
            case "JPA" -> JpaTeacherRepository.getInstance();
            case "SQL" -> SqlTeacherRepository.getInstance();
            case "MEMORY" -> MemoryTeacherRepository.getInstance();
            default -> null;
        };
    }
}
