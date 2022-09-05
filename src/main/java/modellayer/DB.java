package modellayer;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Represents the DB
public class DB {
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
//                System.out.println("The connection was successful");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // The method for getting all of a data.
//    public ResultSet getAllOp(String query) {
//        List<Item> allItems = new ArrayList<>();
//        try {
//            PreparedStatement sqlStatement = this.getDb().prepareStatement();
//            ResultSet result = sqlStatement.executeQuery(query);
//            while (result.next()) {
//                allItems.add(new Book(
//                        result.getString("title"),
//                        result.getString()
//                ))
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}
