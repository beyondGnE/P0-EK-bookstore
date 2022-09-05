package controllerlayer;

import modellayer.Book;
import modellayer.BookStore;
import viewlayer.AddBookMenu;
import viewlayer.BookStoreMenu;
import viewlayer.Menu;
import viewlayer.SearchMenu;

import java.util.List;

public class BookStoreController {

    private BookService service;
    private BookStore bookstore;
    private Menu[] menus;

    public BookStoreController() {
        service = new BookService();
        bookstore = new BookStore();
        menus = new Menu[4];
        menus[0] = new BookStoreMenu();
        menus[1] = new SearchMenu();
        menus[2] = new AddBookMenu();
    }

    public void displayMainView() {
        List<Book> booklist = service.readRecords(bookstore);
        for (Book b : booklist) {
            System.out.println(b);
        }
        menus[0].runMenu(this);
    }

    public void displaySearchView() {
        menus[1].runMenu(this);
        System.out.println("Here are the results:");
        List<Book> results = service.readRecords(bookstore, menus[1].getUserInput());
        if (results.size() == 0) {
            System.out.println("No results found.");
        } else {
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    public void displayAddBookView() {
        menus[2].runMenu(this);
//        if (menus[2].getUserInput())
        service.createRecord(bookstore, menus[2].getUserInput());
    }

    public void displayDeleteBookView() {

    }

    // Opens book search menu
    public void searchBooks() {

    }
}
