package com.aurionpro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private Customer customer;
    private List<LineItem> items;
    private DeliveryBoy deliveryBoy;
    private String timeSlot;
    private String paymentMethod;

    private double gst;
    private double paymentCharge;
    private double finalTotal;

    public Order(Customer customer, List<LineItem> items, DeliveryBoy deliveryBoy,
                 String timeSlot, String paymentMethod) {
        this.customer = customer;
        this.items = items;
        this.deliveryBoy = deliveryBoy;
        this.timeSlot = timeSlot;
        this.paymentMethod = paymentMethod;
        this.gst = 0;
        this.paymentCharge = 0;
        calculateFinalTotal();
    }

    private void calculateFinalTotal() {
        double subtotal = 0;
        for (LineItem item : items) {
            subtotal += item.getItemTotal();  // assumes LineItem calculates price with discount
        }

        this.gst = subtotal * 0.18;
        
        switch (paymentMethod) {
            case "Cash on Delivery" -> this.paymentCharge = 15;
            case "UPI" -> this.paymentCharge = 13;
            case "Card" -> this.paymentCharge = 0;
        }

        this.finalTotal = subtotal + gst + paymentCharge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Order Summary:\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");

        for (LineItem item : items) {
            sb.append(item.getFood().getName()).append(" x").append(item.getQuantity())
              .append(" = ₹").append(String.format("%.2f", item.getItemTotal())).append("\n");
        }

        double subtotal = finalTotal - gst - paymentCharge;
        sb.append("Subtotal: ₹").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("GST (18%): ₹").append(String.format("%.2f", gst)).append("\n");
        sb.append("Payment Charge: ₹").append(String.format("%.2f", paymentCharge)).append("\n");
        sb.append("Total: ₹").append(String.format("%.2f", finalTotal)).append("\n");

        if (deliveryBoy != null) {
            sb.append("Delivery By: ").append(deliveryBoy.getName())
              .append(" (ID: ").append(deliveryBoy.getId()).append(", ")
              .append(timeSlot).append(")\n");
        } else {
            sb.append("Delivery scheduled for tomorrow (No delivery boy assigned today).\n");
        }

        return sb.toString();
    }
}
