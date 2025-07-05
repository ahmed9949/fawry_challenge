package com.example;

public class shippable_product extends product implements shippable_interface {
    private double weight;

    public shippable_product(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public double getweight() {
        return this.weight;
    }
    
}
