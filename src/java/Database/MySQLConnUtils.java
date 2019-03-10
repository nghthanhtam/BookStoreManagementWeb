package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MySQLConnUtils {
    public static Connection getMySQLConnection()
         throws ClassNotFoundException, SQLException { 
     String hostName = "27.78.25.175";//"localhost";
     String dbName = "nhasach";
     String userName = "nhasach";
     String password = "nhasachnhasach";
     return getMySQLConnection(hostName, dbName, userName, password);
}

    public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {
    
     Class.forName("com.mysql.jdbc.Driver");
   
     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
  
     Connection conn = (Connection) DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}
