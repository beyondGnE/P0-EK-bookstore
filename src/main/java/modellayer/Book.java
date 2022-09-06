package modellayer;


import java.util.Date;

public class Book extends Item {
    public Book(String title, Author author,
                double price, String publishDate, String isbn) {
        super(title, price);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setPublishDate(publishDate);
    }

    public Book() {
        this("default", new Author(), 0, null,"default");
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    private Author author;
    private String publishDate;

    private String isbn;

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Title: " + this.getTitle() + "\n");
//        builder.append("Author: " + this.getAuthor() + "\n");
//        builder.append("ISBN: " + this.getIsbn() + "\n");
//        builder.append("Published Date: " + this.getPublishDate() + "\n");
//        builder.append("Price: " + this.getPrice() + "\n");
//        return builder.toString();
//
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getTitle() + "\t");
        builder.append(this.getAuthor() + "\t");
        builder.append(this.getPrice() + "\t");
        builder.append(this.getIsbn());
//        builder.append("Published Date: " + this.getPublishDate() + "\n");

        return builder.toString();

    }
}
