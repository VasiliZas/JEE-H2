package vasilizas.repository;

import vasilizas.bean.Admin;

import java.util.List;

public class AdminRepository {

    public static List<Admin> adminList = List.of(new Admin("Vasili", 33));

    private AdminRepository() {
    }
}
