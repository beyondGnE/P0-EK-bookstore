import modellayer.*;
import viewlayer.BookStoreMenu;

import java.util.ArrayList;
import java.util.List;

public class BookStoreDriver {
    public static void main(String[] args) {

        BookStoreApp bookstoreApp = new BookStoreApp();
        bookstoreApp.testDB(new DB());
        bookstoreApp.runMenu(new BookStoreMenu());
    }
}
