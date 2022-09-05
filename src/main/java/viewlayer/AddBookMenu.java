package viewlayer;

import controllerlayer.BookStoreController;

import java.util.Scanner;

public class AddBookMenu extends Menu {

    public AddBookMenu() {
        super();
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter the following, separated by semicolons (Include no brackets):");
        System.out.println("(The ISBN is required.)");
        System.out.println("[ISBN]; [title]; [author first name]; [author last name]; [publish date]");
    }

    @Override
    public void evaluateResponse(BookStoreController c) {

        switch(this.getUserInput()) {
            case "back": case "BACK": case "b": case "B": break;
            case "Exit": case "EXIT": case "exit": case "e": case "x":
                System.exit(0);
                break;
            default:
                this.setIsGood(true);
                break;
        }

    }
}
