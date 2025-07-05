package com.example;

public class cart_item {
    private product product;
private int quantity;

    public cart_item(product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getfinalproductprice() {
        return product.getPrice() * quantity;
    }
}
