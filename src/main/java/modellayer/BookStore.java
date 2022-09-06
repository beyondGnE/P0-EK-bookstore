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
                        result.getString("publish_date"),
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
                        result.getString("publish_date"),
                        result.getString("isbn")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    // Check if the author is already in the db
    // If it is, update newBook's author object state with that author.
    // and then add the newBook.
    // If not, then
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

                // Must check if the author already exists
                List<Author> possibleAuthors = this.getListOfAuthorsByName(newBook.getAuthor().getFirst_name(), newBook.getAuthor().getLast_name());
                if (possibleAuthors.size() == 0) { // Add the new author to the table.
                    this.addNewAuthor(newBook.getAuthor()); // Add the new author, triggering the auto id,
                    newBook.getAuthor().setAuthor_id(this.getAuthorMaxId()); // Then get the newly generated author id from table.
                } else { // Keep the author;
                    newBook.getAuthor().setAuthor_id(possibleAuthors.get(0).getAuthor_id());
                }


                // Okay, now the actual adding of the book.
                String query = "insert into book(isbn, title, author_id, publish_date, price) " +
                        "values (?, ?, ?, ?, ?)";
                try {
                    PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
                    sqlStatement.setString(1, newBook.getIsbn());
                    sqlStatement.setString(2, newBook.getTitle());
                    sqlStatement.setInt(3, newBook.getAuthor().getAuthor_id());
                    sqlStatement.setString(4, newBook.getPublishDate());
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

    public int getAuthorMaxId() {
        List<Author> searchResult = new ArrayList<>();
        int maxid = 0;
        String query = "SELECT max(author_id) from author;";
        try {
            Statement sqlStatement = db.getDb().createStatement();
            ResultSet result = sqlStatement.executeQuery(query);
            if (result.next()) {

                maxid = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxid;
    }

    public void deleteBookByIsbn(String input) {
        String query = "delete from book where isbn = ?";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
            sqlStatement.setString(1, input);
            int x = sqlStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookByIsbn(String input) {
        String[] formatted = input.split(";");
        String query = "update book " +
                "set isbn = ?, title = ?, " +
                "publish_date = ?, price = ? " +
                "where isbn = ?;";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
            sqlStatement.setString(1, formatted[0]);
            sqlStatement.setString(2, formatted[1]);
            sqlStatement.setString(3, formatted[2]);
            sqlStatement.setString(4, formatted[3]);
            sqlStatement.setString(5, formatted[0]);
            int x = sqlStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getListOfAuthorsByName(String first_name, String last_name) {
        List<Author> authorList = new ArrayList<>();
        String query = "select * from author where first_name = ? and last_name = ?"; // *****
        if (first_name.equals("") && last_name.equals("")) {
            try {
                PreparedStatement sqlStatement = db.getDb().prepareStatement(query);
                sqlStatement.setString(1, first_name);
                sqlStatement.setString(2, last_name);
                ResultSet result = sqlStatement.executeQuery();
                while (result.next()) {
                    if (!result.getString("first_name").equals("") &&
                            !result.getString("last_name").equals("")) { // Add only if both first and last names are not ""
                        authorList.add(new Author(result.getInt("author_id"),
                                result.getString("first_name"),
                                result.getString("last_name")));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return authorList;
    }

    public void addNewAuthor(Author newAuthor) {
        String authorQuery = "insert into author(first_name, last_name) values (?, ?)";
        try {
            PreparedStatement sqlStatement = db.getDb().prepareStatement(authorQuery);
            sqlStatement.setString(1, newAuthor.getFirst_name());
            sqlStatement.setString(2, newAuthor.getLast_name());
            sqlStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
