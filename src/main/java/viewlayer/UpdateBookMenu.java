package viewlayer;

import controllerlayer.BookStoreController;

public class UpdateBookMenu extends Menu {
    public UpdateBookMenu() {
        super();
    }

    @Override
    public void displayMenu() {
        System.out.println("Enter the following, separated by semicolons (Include no brackets):");
        System.out.println("(The ISBN is required.)");
        System.out.println("[ISBN]; [title]; [publish date]; [price]");    }

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
