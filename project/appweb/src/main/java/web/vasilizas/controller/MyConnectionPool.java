package web.vasilizas.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionPool {
    private static MyConnectionPool instance;

    private MyConnectionPool() {
        //private constructor
    }

    public static synchronized MyConnectionPool getInstance() {
        if (instance == null)
            instance = new MyConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection con = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myPool");
            con = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
