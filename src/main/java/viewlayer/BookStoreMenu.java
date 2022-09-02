package viewlayer;

import java.util.Scanner;

public class BookStoreMenu implements IMenu {

    private String userInput;

    public void displayMenu() {
        System.out.println("Please select a choice: ");
        System.out.println("1. [S]earch for books");
        System.out.println("2. [P]urchase books");
        System.out.println("3. [O]pen Shopping Cart");
    }

    // Take in input and sanitize it.
    public void acceptInput() {
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
    }

    public void evaluateResponse() {
        switch(userInput) {
            case "s": case "S": case "search": case "Search": case "SEARCH":
            case "search books": case "SEARCH BOOKS": case "search for books":
            case "SEARCH FOR BOOKS":
                System.out.println("A method here will handle book searching");
                break;
            case "p": case "P": case "purchase books": case "PURCHASE BOOKS":
            case "purchase":
                System.out.println("A method here will handle book purchasing");
                break;
            case "O": case "o": case "shopping cart": case "SHOPPING CART": case "open shopping cart":
            case "open cart": case "OPEN SHOPPING CART": case "OPEN CART": case "Open Shopping Cart":
                System.out.println("A method here will handle opening the cart and viewing it");
        }
    }

    public void displaySearchMenu() {
        System.out.println("Search books by");
        System.out.println("[T]itle");
        System.out.println("[A]uthor");
        System.out.println("[D]ate Published");

    }

}
