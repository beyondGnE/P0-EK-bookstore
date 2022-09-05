import controllerlayer.BookService;
import controllerlayer.BookStoreController;
import modellayer.Book;
import viewlayer.*;
import modellayer.BookStore;

import java.util.List;

public class BookStoreApp {

    private BookStoreController controller;

    public BookStoreApp() {
        controller = new BookStoreController();
        controller.displayMainView();
    }


}
