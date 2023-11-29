package hong.everything.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hong.everything.repository.ConnectionConst.*;

public class DBConnectionutil {
    public static Connection getConnection(){
        try {
            Connection connection= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
