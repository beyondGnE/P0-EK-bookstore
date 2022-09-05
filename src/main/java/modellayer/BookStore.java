package modellayer;

import com.nimbusds.oauth2.sdk.util.date.SimpleDate;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

// The repo, I guess
// The main core of the app; everything else attaches to it.
// Handles interfacing with the DB
// Is called by the Service

public class BookStore {
    private DB db;

    public BookStore() {
        db = new DB();
        db.connectToDB();
    }

    public List<Book> getBookInventory() {
        List<Book> allItems = new ArrayList<>();
        String query = "SELECT * FROM book inner join author " +
                        "on author.author_id = book.author_id;";
        try {
            Statement sqlStatement = db.getDb().createStatement();
            ResultSet result = sqlStatement.executeQuery(query);
            while (result.next()) {
                allItems.add(new Book(
                        result.getString("title"),
                        new Author(result.getInt("author_id"),
                                result.getString("first_name"),
                                result.getString("last_name")),
                        result.getDouble("price"),
                        result.getDate("publish_date"),
                        result.getString("isbn")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allItems;
    }



    public List<Book> getBookByISBN(String isbn) {
        List<Book> searchResult = new ArrayList<>();
        String query = "SELECT * FROM book inner join author " +
                "on author.author_id = book.author_id " +
                "where isbn = ?;";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
            sqlStatement.setString(1, isbn);
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                searchResult.add(new Book(
                        result.getString("title"),
                        new Author(result.getInt("author_id"),
                                result.getString("first_name"),
                                result.getString("last_name")),
                        result.getDouble("price"),
                        result.getDate("publish_date"),
                        result.getString("isbn")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public void addBookByIsbn(String input) {
        String[] formatted = input.split(";");

        if (formatted[0].matches("^(97(8|9))?-\\d{9}(\\d|X)$")) { // Valid format if true


            // First get any possible matches
            List<Book> searchResult = this.getBookByISBN(formatted[0]);

            // Now check if that isbn already exists.
            if (searchResult.size() == 0) { // If no matches returned, then add it.
                Book newBook = new Book();
                newBook.setIsbn(formatted[0]);
                newBook.setPrice(0);
                newBook.setTitle(formatted.length > 1 ? formatted[1] : "");
//                newBook.setPublishDate(formatted.length >= 5 ? DateFormat.parse(formatted[4]) : null);
                newBook.setAuthor(formatted.length >= 4 ? new Author(0, formatted[2], formatted[3]) : new Author());

                // Must check if the author already exists
                List<Author> possibleAuthors = this.getListOfAuthorsByName(newBook.getAuthor().getFirst_name(), newBook.getAuthor().getLast_name());
                if (possibleAuthors.size() == 0) { // Add the new author to the table.
                    int newId = this.findRecentAuthor_id();
                    newBook.getAuthor().setAuthor_id(++newId);
                    this.addNewAuthor(newBook.getAuthor());
                }

                // Okay, now the actual adding of the book.
                String query = "insert into book(isbn, title, author_id, publish_date, price) " +
                        "values (?, ?, ?, ?, ?)";
                try {
                    PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
                    sqlStatement.setString(1, newBook.getIsbn());
                    sqlStatement.setString(2, newBook.getTitle());
                    sqlStatement.setInt(3, newBook.getAuthor().getAuthor_id());
                    sqlStatement.setDate(4, (java.sql.Date) newBook.getPublishDate());
                    sqlStatement.setDouble(5, newBook.getPrice());
                    sqlStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Invalid ISBN format");
        }
    }

    public void deleteBookByIsbn(String input) {

    }

    public List<Author> getListOfAuthorsByName(String first_name, String last_name) {
        List<Author> authorList = new ArrayList<>();
        String query = "select author_id from author where first_name = ? and last_name = ?";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
            sqlStatement.setString(1, first_name);
            sqlStatement.setString(2, last_name);
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                authorList.add(new Author(result.getInt("author_id"),
                        result.getString("first_name"),
                        result.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public int findRecentAuthor_id() {
        String query = "select max(author_id) from author;";
        int max_id = 0;
        try {
            Statement sqlStatement = db.getDb().createStatement();
            ResultSet result = sqlStatement.executeQuery(query);
            max_id = result.getInt("author_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max_id;
    }

    public void addNewAuthor(Author newAuthor) {
        String authorQuery = "insert into author(author_id, first_name, last_name) values (?, ?, ?)";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(authorQuery);
            sqlStatement.setInt(1, newAuthor.getAuthor_id());
            sqlStatement.setString(2, newAuthor.getFirst_name());
            sqlStatement.setString(3, newAuthor.getLast_name());
            sqlStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
