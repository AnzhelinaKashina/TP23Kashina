import java.sql.*;
class ConnectionManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/organizapp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "anzela12345";

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

}
