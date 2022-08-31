package viewlayer;

import java.util.Scanner;

public class BookStoreMenu implements IMenu {

    public void displayMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select a choice: ");
        System.out.println("1. [S]earch for books");
        System.out.println("2. [S]earch for books");
        System.out.println("3. [S]earch for books");
        String input = scan.nextLine();
        switch(input) {
            case "s":
            case "S":
            case "search":
            case "Search":
            case "SEARCH":
                //
        }
    }
}
