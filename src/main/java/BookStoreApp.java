import controllerlayer.BookStoreController;
import io.javalin.Javalin;

public class BookStoreApp {

    private BookStoreController controller;

    public BookStoreApp() {
        controller = new BookStoreController();
        controller.displayMainView();

    }


}
