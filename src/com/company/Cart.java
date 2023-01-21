package com.company;

import java.util.Map;
import java.util.UUID;

public class Cart {

    private Customer customer;
    private UUID discountsId;
    private Map<Product, Integer> productMap;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Cart(Customer customer, UUID discountsId, Map<Product, Integer> productMap) {
        this.customer = customer;
        this.discountsId = discountsId;
        this.productMap = productMap;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(UUID discountsId) {
        this.discountsId = discountsId;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public Double calculateCartTotalAmount(){
        double totalAmount = 0;

        for (Product product : productMap.keySet()) {
            totalAmount+= product.getPrice()  * productMap.get(product);

        }
        return totalAmount;
    }
}
