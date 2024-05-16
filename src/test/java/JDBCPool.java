import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class JDBCPool {
    private static JDBCPool instance;
    //定义一个集合来存放工具类中的Connection对象
    private ArrayList<Connection> connections;
    //数据库最大容量 5
    private int size = 5;

    //模拟数据库连接池获取connection对象
    private JDBCPool() {
        connections = new ArrayList<>();
        connections.add(JDBCUtils.getInstance().getConnection());
    }

    public static JDBCPool getInstance() {
        if (instance == null) {
            instance = new JDBCPool();
        }
        return instance;
    }

    //连接池获取连接对象
    public Connection getConnection() {
        if (connections.isEmpty()) {
            return null;
        }
        //生成一个0~4的随机数，使系统随机连接获取一个连接对象
        Random random = new Random();
        int result = random.nextInt(size);
        Connection connection = connections.get(0);
        //获取当前随机对象后，将该对象从数据库对象池中移除，调用完毕后放回
        connections.remove(connection);
        return connection;
    }
    //连接对象使用完毕后，将该连接对象放回集合中
    public void backConnection(Connection con){
        if (con != null){
            connections.add(con);
        }
    }
}
