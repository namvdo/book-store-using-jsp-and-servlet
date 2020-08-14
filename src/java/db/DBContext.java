package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBContext {
    
    private final String serverName = "localhost";
    private final String dbName = "BookStore";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "nam123";
    private static DBContext instance;
    private static Connection connection;
    
    public Connection getConnection()throws Exception {
        return connection;
    }
    
    private DBContext() {
        try {
            String url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, userID, password);
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }
 
}