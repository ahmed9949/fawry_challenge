package com.example;

import java.util.ArrayList;
import java.util.List;

public class cart {
    private List<cart_item> items;
    private customer customer;
    private shippingservice shippingservice = new shippingservice();

    cart() {
        this.items = new ArrayList<>();
        this.customer = null;
    }

    public List<cart_item> getItems() {
        return items;
    }

    public void setItems(List<cart_item> items) {
        this.items = items;
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public double get_total_price(List<cart_item> items) {
        double total = 0.0;
        for (cart_item item : items) {
            if (item != null) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    public double shipping_fee(List<cart_item> items) {
        double totalWeight = 0.0;

        for (cart_item item : items) {
            if (item != null) {
                if (item.getProduct() instanceof shippable_interface) {
                    shippable_interface shippableProduct = (shippable_interface) item.getProduct();
                    totalWeight += shippableProduct.getweight() * item.getQuantity();
                }
            }
        }

        return shippingservice.shipping_fee * totalWeight;
    }

    public boolean chekcart_empty() {
        if (this.items.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean chekcustomer_balance() {
        double subtotal = this.get_total_price(this.items);
        double shippingCost = this.shipping_fee(this.items);
        double totalAmount = subtotal + shippingCost;

        if (this.customer.getBalance() >= totalAmount) {
            return true;
        } else {
            return false;
        }
    }

    public boolean check_product_stock(List<cart_item> items) {
        for (cart_item item : items) {
            if (item != null && !item.getProduct().stockavailable()) {
                return false;
            }
        }
        return true;
    }

    public boolean check_product_availability(List<cart_item> items) {
        for (cart_item item : items) {
            if (item != null && !item.getProduct().isavailable(item.getQuantity())) {
                return false;
            }
        }
        return true;
    }

    public boolean check_product_availability_by_expiration(List<cart_item> items) {
        for (cart_item item : items) {
            if (item != null) {
                if (item.getProduct() instanceof expirable_interface) {
                    expirable_interface expirableProduct = (expirable_interface) item.getProduct();
                    if (expirableProduct.isExpired()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void edit_customer_balance(customer customer, double amount) {
        if (customer != null) {
            double newBalance = customer.getBalance() + amount;
            customer.setBalance(newBalance);
        }
    }

    public void edit_product_quantity(List<cart_item> items) {
        for (cart_item item : items) {
            if (item != null) {
                product product = item.getProduct();
                product.setQuantity(product.getQuantity() - item.getQuantity());
            }
        }
    }

    public void checkout(cart cart, customer customer) {
        if (cart.chekcart_empty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        if (!cart.check_product_availability(cart.getItems())) {
            System.out.println("Some products are not available in stock.");
            return;
        }
        if (!cart.check_product_availability_by_expiration(cart.getItems())) {
            System.out.println("Some products in your cart have expired.");
            return;
        }
        if (!cart.chekcustomer_balance()) {
            System.out.println("You don't have enough balance to complete the purchase.");
            return;
        }
        if (!cart.check_product_stock(cart.getItems())) {
            System.out.println("Some products are out of stock.");
            return;
        }

        double subtotal = cart.get_total_price(cart.getItems());
        double shippingFees = cart.shipping_fee(cart.getItems());
        double paidAmount = subtotal + shippingFees;

        customer.setBalance(customer.getBalance() - paidAmount);
        cart.edit_product_quantity(cart.getItems());

        cart.shippingservice.processShipping(cart.getItems());

        System.out.println("=== CHECKOUT DETAILS ===");
        System.out.println("Order Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("Shipping Fees: $" + String.format("%.2f", shippingFees));
        System.out.println("Paid Amount: $" + String.format("%.2f", paidAmount));
        System.out.println("Customer Current Balance After Payment: $" + String.format("%.2f", customer.getBalance()));
        System.out.println("Checkout successful!");
    }
}
