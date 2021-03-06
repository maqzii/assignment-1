package com.amaral.model;

public class Game {
    private String name;
    private double price;
    private int quantity;
    private Publisher publisher;

    public enum Publisher {
        IDSOFTWARE, BUNGIE, NAMCO
    }

    public Game(String name, double price, int quantity, Publisher publisher) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
} // END OF Game
