package vasilizas.repository;

import vasilizas.bean.Admin;

import java.util.List;

public class AdminRepository {

    public static final List<Admin> adminList = List.of(new Admin("Vasili", 33, "mylogin", "456987"));

    private AdminRepository() {
        // blank default constructor for utility class
    }
}
