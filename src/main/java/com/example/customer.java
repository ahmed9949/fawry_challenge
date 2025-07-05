package com.example;

public class customer {
    private String name;
    private double balance;

    public customer(String name, double balance) {
        this.name = name;
        this.balance = balance;

    }

    public boolean hasbalance(double amounot) {
        return this.balance >= amounot;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
