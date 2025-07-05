package com.example;

import java.time.LocalDate;

public class expirableandshippppable_product extends product implements expirable_interface, shippable_interface {
    private LocalDate expirationDate;
    private double weight;

    public expirableandshippppable_product(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public double getweight() {
        return this.weight;
    }
}
