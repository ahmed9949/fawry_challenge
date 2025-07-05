package com.example;

import java.util.ArrayList;
import java.util.List;

public class shippingservice {
    public static final double shipping_fee = 50.0;

    public shippingservice() {
    }

    public void processShipping(List<cart_item> items) {
        List<ShippableItem> shippableItems = new ArrayList<>();

        for (cart_item item : items) {
            if (item != null && item.getProduct() instanceof shippable_interface) {
                shippable_interface shippableProduct = (shippable_interface) item.getProduct();

                // Add each quantity as separate shipping item
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(new ShippableItem(
                            shippableProduct.getName(),
                            shippableProduct.getweight()));
                }
            }
        }

        if (!shippableItems.isEmpty()) {
            System.out.println("\n=== SHIPPING PROCESSING ===");
            System.out.println("Items being shipped:");

            double totalWeight = 0.0;
            for (ShippableItem item : shippableItems) {
                System.out.println("- " + item.getName() + " (Weight: " + item.getweight() + " kg)");
                totalWeight += item.getweight();
            }

            System.out.println("Total shipping weight: " + totalWeight + " kg");
            System.out.println("Items sent to shipping service successfully!");
        }
    }

    public static class ShippableItem {
        private String name;
        private double weight;

        public ShippableItem(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public double getweight() {
            return weight;
        }
    }
}