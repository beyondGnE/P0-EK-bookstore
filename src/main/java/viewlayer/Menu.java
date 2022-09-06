package viewlayer;

import controllerlayer.BookStoreController;

import java.util.Scanner;

public abstract class Menu {
    public Menu() {
        userInput = "";
        isGood = false;
    }
    private String userInput;
    private boolean isGood;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }


    public abstract void displayMenu();

//    public abstract void acceptInput();
    // Take in input and sanitize it.
    public void acceptInput() {
        Scanner scan = new Scanner(System.in);
        this.setUserInput(scan.nextLine());
    }

    public boolean getIsGood() {
        return isGood;
    }

    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }

    public abstract void evaluateResponse(BookStoreController c);

    public void runMenu(BookStoreController c) {
        while ((!this.getUserInput().equals("back") &&
                !this.getUserInput().equals("BACK") &&
                !this.getUserInput().equals("B") &&
                !this.getUserInput().equals("b")) &&
                !this.getIsGood()) {
//            System.out.println(this.getUserInput());
            this.displayMenu();
            this.acceptInput();
            this.evaluateResponse(c);
        }
        System.out.println(this.getUserInput());
        this.setIsGood(false); // Switch it back off.
    }

//    public abstract void runMenu(BookStoreController c);
}
