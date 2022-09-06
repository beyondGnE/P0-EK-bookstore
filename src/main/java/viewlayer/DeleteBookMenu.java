package viewlayer;

import controllerlayer.BookStoreController;

public class DeleteBookMenu extends Menu {
    public DeleteBookMenu() {
        super();
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter the book's ISBN to delete:");
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
