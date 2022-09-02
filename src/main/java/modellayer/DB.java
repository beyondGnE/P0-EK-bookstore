package modellayer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Represents the DB
public class DB implements IConnection {
    private Connection db;

    public Connection getDb() {
        return db;
    }

    public void setDb(Connection db) {
        this.db = db;
    }

    public DB() {
        db = null;
    }
    public void connectToDB() {
        if (db == null) {
            String URL = "jdbc:sqlserver://localhost:1433;"+
                    "databaseName=bookstore;"+
                    "TrustServerCertificate=True;"+
                    "user=sa;password=P@SSWORD123;";
            try {
                db = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
