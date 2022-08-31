package modellayer;

public class Book extends Item {
    public Book(String title, String author, double price, String isbn) {
        super(title, price);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }

    public Book() {
        this("default", "default", 0, "default");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    private String author;
    private String isbn;
}
