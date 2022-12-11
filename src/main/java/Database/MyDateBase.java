package Database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class MyDateBase implements Closeable {
    public MyDateBase () {
        this.connect();
    }
    private Connection connection = null; //все переменные для подключения

    public Connection getConnection() {
        return this.connection;
    }

    private String url = "jdbc:postgresql://localhost:5432/dbtest";
    private String username = "postgres";
    private String password = "postgres";

    private void connect() {
        try {
            System.out.println("Подключение к базе данных");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Что-то не так");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
