import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectFactory {
    static Connection conn = null;

    private ConnectFactory() {
    }

    public static Connection getConnection(){
        //*******************************************************************
        try(InputStream input = new FileInputStream("src/config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            //String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            //Class.forName(driver);
            conn= DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return conn;
    }

}
