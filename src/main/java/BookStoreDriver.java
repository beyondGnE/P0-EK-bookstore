import modellayer.Book;
import viewlayer.BookStoreMenu;

import java.util.ArrayList;
import java.util.List;

public class BookStoreDriver {
    public static void main(String[] args) {

        BookStoreApp bookstoreApp = new BookStoreApp();
        List<Book> inventory = new ArrayList<>();
        BookStoreMenu menu = new BookStoreMenu();
        bookstoreApp.runMenu(menu);
        menu.displayMenu();
    }
}
