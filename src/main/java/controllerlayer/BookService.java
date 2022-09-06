package controllerlayer;

import modellayer.Book;
import modellayer.BookStore;

import java.util.List;

/*
The "API" layer
Handles general CRUD operations
Called by the main app.
 */
public class BookService {

    // Create
    public void createRecord(BookStore b, String input) {
        b.addBookByIsbn(input);
//        System.out.println("The book was added successfully");
    }

    // Read
    public List<Book> readRecords(BookStore b) {
        return b.getBookInventory();
    }

    // Read
    public List<Book> readRecords(BookStore b, String isbn) {
        return b.getBookByISBN(isbn);
    }

    // Update
    public void updateRecord(BookStore b, String isbn) {
        b.updateBookByIsbn(isbn);
    }

    // Delete
    public void deleteRecord(BookStore b, String isbn) {
        b.deleteBookByIsbn(isbn);
    }
}
