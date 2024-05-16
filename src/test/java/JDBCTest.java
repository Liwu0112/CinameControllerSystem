import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCTest {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        JDBCPool jdbcPool = JDBCPool.getInstance();
        connection = jdbcPool.getConnection();
        if (connection != null) {
            try {
                String sql = "select * from zengjing_book";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.print(resultSet.getInt(1) + "\t");
                    System.out.print(resultSet.getString(2) + "\t");
                    System.out.print(resultSet.getString(3) + "\t");
                    System.out.print(resultSet.getString(4) + "\n");
                }
                resultSet.close();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //归还连接对象
                jdbcPool.backConnection(connection);
            }
        }
    }
}
