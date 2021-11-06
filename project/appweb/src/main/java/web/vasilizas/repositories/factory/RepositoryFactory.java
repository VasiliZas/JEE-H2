package web.vasilizas.repositories.factory;


import web.vasilizas.repositories.Repository;

public class RepositoryFactory {

    public static Repository getTypePerson(String typeRepository, String typeUser) {
        return switch (typeRepository) {
            case "JPA" -> JpaFactory.getTypePersonRepository(typeUser);
            case "SQL" -> SqlFactory.getTypePersonRepository(typeUser);
            default -> null;
        };
    }
}
