package com.company.order;

import com.company.Product;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Order {
    private UUID id;
    private LocalDateTime orderDate;
    private Double cartTotalAmount;
    private Double paidAmount;
    private Double discountAmount;
    private UUID customerId;
    private String orderStatus;
    private Set<Product> productList;

    public Order(UUID id, LocalDateTime orderDate, Double cartTotalAmount, Double paidAmount, Double discountAmount, UUID customerId, String orderStatus, Set<Product> productList) {
        this.id = id;
        this.orderDate = orderDate;
        this.cartTotalAmount = cartTotalAmount;
        this.paidAmount = paidAmount;
        this.discountAmount = discountAmount;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.productList = productList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getCartTotalAmount() {
        return cartTotalAmount;
    }

    public void setCartTotalAmount(Double cartTotalAmount) {
        this.cartTotalAmount = cartTotalAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }
}
