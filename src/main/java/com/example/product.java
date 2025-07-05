package com.example;

public class product {
    private String name;
    private double price;
    private int quantity;

    public product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isavailable(int quantity) {
        return quantity <= this.quantity;
    }

    public boolean stockavailable() {
        return this.quantity > 0;
    }
 
}
