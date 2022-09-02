import viewlayer.IMenu;
import modellayer.*;

public class BookStoreApp {
    private String userInput;

    public void runMenu(IMenu menu) {
        while (true) {
            menu.displayMenu();
            menu.acceptInput();
            menu.evaluateResponse();
        }
    }

    public void testDB(IConnection test) {
        test.connectToDB();
    }

}
