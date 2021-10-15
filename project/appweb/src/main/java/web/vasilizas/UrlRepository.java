package web.vasilizas;

import java.util.Map;

public class UrlRepository {
    public static final Map<String, String> urlMap = Map.of(
            "Student", "/myweb/student",
            "Admin", "/myweb/admin",
            "Teacher", "/myweb/teacher",
            "Error", "/myweb/error");

    private UrlRepository() {
    }
}
