package com.example;

import java.time.LocalDate;

public class Main {
        public static void main(String[] args) {
                System.out.println("fawry task test \n");

                product scratchCard = new product("kart sha7n", 10.0, 50);

                shippable_product tv = new shippable_product("tv", 500.0, 5, 15.0);
                shippable_product mobile = new shippable_product("iphone 16 pro", 999.0, 10, 0.5);

                expirable_product milk = new expirable_product("laban bkhero", 3.0, 20, LocalDate.now().plusDays(5));
                expirable_product expiredBread = new expirable_product("richbake", 2.0, 10,
                                LocalDate.now().minusDays(2));

                expirableandshippppable_product cheese = new expirableandshippppable_product(
                                "cheddar sauce", 8.0, 15, LocalDate.now().plusDays(10), 0.5);
                expirableandshippppable_product biscuits = new expirableandshippppable_product(
                                "chocolate", 5.0, 30, LocalDate.now().plusDays(30), 0.3);

                System.out.println("\n  successful checkout");
                customer customer1 = new customer("fess", 2000.0);
                System.out.println("Customer: " + customer1.getName() + " (Balance: $" + customer1.getBalance() + ")");

                cart cart1 = new cart();
                cart1.setCustomer(customer1);

                cart1.getItems().add(new cart_item(scratchCard, 2));
                cart1.getItems().add(new cart_item(tv, 1));
                cart1.getItems().add(new cart_item(cheese, 3));
                cart1.getItems().add(new cart_item(biscuits, 2));

                System.out.println("\nItems in cart:");
                for (cart_item item : cart1.getItems()) {
                        System.out.println("- " + item.getProduct().getName() + " x" + item.getQuantity() + " = $"
                                        + item.getfinalproductprice());
                }

                cart1.checkout(cart1, customer1);

                System.out.println("\nempty cart ");
                customer customer2 = new customer("ahmed ", 1000.0);
                cart emptyCart = new cart();
                emptyCart.setCustomer(customer2);

                emptyCart.checkout(emptyCart, customer2);

                System.out.println("\n test insufficient balance ");
                customer poorCustomer = new customer("mansour", 50.0);
                System.out.println("Customer: " + poorCustomer.getName() + " (Balance: $" + poorCustomer.getBalance()
                                + ")");

                cart poorCart = new cart();
                poorCart.setCustomer(poorCustomer);
                poorCart.getItems().add(new cart_item(tv, 1));

                System.out.println("Trying to buy: " + tv.getName() + " - $" + tv.getPrice());
                poorCart.checkout(poorCart, poorCustomer);

                System.out.println("\nput of stock test ");
                customer customer3 = new customer("tata", 5000.0);
                cart outOfStockCart = new cart();
                outOfStockCart.setCustomer(customer3);

                outOfStockCart.getItems().add(new cart_item(tv, 10));

                System.out.println("Trying to buy 10 TVs, but only " + tv.getQuantity() + " available");
                outOfStockCart.checkout(outOfStockCart, customer3);

                System.out.println("\n=== TEST CASE 5: EXPIRED PRODUCT ===");
                customer customer4 = new customer("mada", 100.0);
                cart expiredCart = new cart();
                expiredCart.setCustomer(customer4);
                expiredCart.getItems().add(new cart_item(expiredBread, 1));

                System.out.println(
                                "Trying to buy expired bread (expired on: " + expiredBread.getExpirationDate() + ")");
                expiredCart.checkout(expiredCart, customer4);

                System.out.println("\n=== TEST CASE 6: MIXED CART (SHIPPABLE + NON-SHIPPABLE) ===");
                customer customer5 = new customer("fares", 1500.0);
                cart mixedCart = new cart();
                mixedCart.setCustomer(customer5);

                mixedCart.getItems().add(new cart_item(scratchCard, 5));
                mixedCart.getItems().add(new cart_item(mobile, 1));
                mixedCart.getItems().add(new cart_item(milk, 2));

                System.out.println("\nMixed cart contents:");
                for (cart_item item : mixedCart.getItems()) {
                        String type = "";
                        if (item.getProduct() instanceof shippable_interface
                                        && item.getProduct() instanceof expirable_interface) {
                                type = " (Shippable + Expirable)";
                        } else if (item.getProduct() instanceof shippable_interface) {
                                type = " (Shippable)";
                        } else if (item.getProduct() instanceof expirable_interface) {
                                type = " (Expirable)";
                        } else {
                                type = " (Digital/Non-shippable)";
                        }
                        System.out.println("- " + item.getProduct().getName() + " x" + item.getQuantity() + type);
                }

                mixedCart.checkout(mixedCart, customer5);

        }
}