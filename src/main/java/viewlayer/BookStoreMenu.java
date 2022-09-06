package viewlayer;

import controllerlayer.BookStoreController;

import java.util.Scanner;

public class BookStoreMenu extends Menu {


    public BookStoreMenu() {
        super();
    }

    @Override
    public void displayMenu() {
        System.out.println("Please select a choice: ");
        System.out.println("[V]iew Booklist");
        System.out.println("[S]earch for books");
        System.out.println("[A]dd book");
        System.out.println("[U]pdate book");
        System.out.println("[D]elete book");
        System.out.println("(Type 'exit' to quit)");
    }


    @Override
    public void evaluateResponse(BookStoreController c) {
        switch(this.getUserInput()) {
            case "v": case "V": case "view": case "VIEW": case "View":
            case "view booklist": case "VIEW BOOKLIST": case "booklist":
            case "BOOKLIST":
                c.displayMainView();
                break;
            case "s": case "S": case "search": case "Search": case "SEARCH":
            case "search books": case "SEARCH BOOKS": case "search for books":
            case "SEARCH FOR BOOKS":
                System.out.println("A method here will handle book searching");
                c.displaySearchView();
                break;
            case "a": case "A": case "add book": case "ADD BOOK":
            case "add": case "ADD":
                System.out.println("A method here will handle book adding");
                c.displayAddBookView();
                break;
            case "U": case "u": case "update book": case "UPDATE BOOK": case "update":
            case "UPDATE":
                System.out.println("A method here will handle updating book");
                c.displayUpdateBookView();
                break;
            case "d": case "D": case "delete book": case "DELETE BOOK": case "delete":
            case "DELETE":
                System.out.println("A method here will handle deleting book");
                c.displayDeleteBookView();
                break;
            case "Exit": case "EXIT": case "exit": case "e": case "x": case "b": case "B": case "back": case "BACK":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

}
