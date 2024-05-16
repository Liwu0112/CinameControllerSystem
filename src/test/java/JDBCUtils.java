import java.sql.*;

public class JDBCUtils {
    private static JDBCUtils instance;
    private Connection connection;

    private JDBCUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JDBCUtils getInstance() {
        if (instance == null) {
            instance = new JDBCUtils();
        }
        return instance;
    }

    //获取数据库连接对象
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/zengjing?serverTimezone=GMT%2B8&useSSL=false";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //释放资源
    public void closeConnection() {
        try {
            if (connection != null && connection.isClosed() == false) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
