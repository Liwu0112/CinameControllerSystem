import java.net.InetAddress;

public class Test03 {
    public static void main(String[] args) {
        try{
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.getHostName());
            System.out.println(localHost.getHostAddress());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
