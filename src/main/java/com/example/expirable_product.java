package com.example;

import java.time.LocalDate;

public class expirable_product extends product implements expirable_interface {
    private LocalDate expirationDate;

    public expirable_product(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
}
