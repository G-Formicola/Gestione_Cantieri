package Entity.DataB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance = null ;
    private Connection connection = null ;
    private final String USERNAME = "Giorgio";
    private final String PASSWORD = "";
    private final String IP = "127.0.0.1";
    private final String PORT = "5433";
    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/gestionecantieri_unina";

    private DBConnection() throws SQLException {

        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBConnection();
        }
        else
        if (instance.getConnection().isClosed())
        {
            instance = new DBConnection();
        }

        return instance;
    }
}
