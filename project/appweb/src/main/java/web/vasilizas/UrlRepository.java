package web.vasilizas;

import java.util.Map;

public class UrlRepository {
    public static final Map<String, String> urlMap = Map.of(
            "Student", "/myweb/student/student.html",
            "Admin", "/myweb/admin/admin.html",
            "Teacher", "/myweb/teacher/teacher.html",
            "Error", "/myweb/error/auth-error.html");

    private UrlRepository() {
    }
}
