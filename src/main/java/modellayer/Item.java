package modellayer;

public abstract class Item {

    public Item(String title, double price) {
        this.setTitle(title);
        this.setPrice(price);
    }

    public Item() {
        this("default", 0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String title;
    private double price;
}
