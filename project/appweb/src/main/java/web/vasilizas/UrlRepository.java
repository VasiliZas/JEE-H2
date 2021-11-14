package web.vasilizas;

import java.util.Map;

public final class UrlRepository {
    public static final Map<String, String> urlMap = Map.of(
            "Student", "/myweb/student",
            "Admin", "/myweb/admin/admin",
            "Teacher", "/myweb/teacher/teacher",
            "Error", "/myweb/error",
            "Auth", "/myweb/auth",
            "Start", "/myweb/start");

    private UrlRepository() {
    }
}
