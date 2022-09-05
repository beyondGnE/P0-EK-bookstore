package viewlayer;

import controllerlayer.BookStoreController;

import java.util.Scanner;

public class SearchMenu extends Menu {

    public SearchMenu() {
        super();
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter a book ISBN: (Type 'back' to go back to main menu)");
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
