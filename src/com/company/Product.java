package com.company;

import com.company.category.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private Double price;
    private Integer stock;
    private Integer remainingStock;
    private UUID categoryId;

    public Product(UUID id, String name, Double price, Integer stock, Integer remainingStock, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.remainingStock = remainingStock;
        this.categoryId = categoryId;
    }

    public String getCategoryName() throws Exception {
        for (Category category : StaticConstance.CATEGORY_LIST) {
            if (getCategoryId().toString().equals(category.getId().toString())){
                return category.getName();
            }
        }
        throw new Exception("Category not found, " + getName());//Category not found, Milk
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setRemainingStock(Integer remainingStock) {
        this.remainingStock = remainingStock;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }
    public LocalDateTime getDeliveryDueDate() throws Exception{
        for(Category category : StaticConstance.CATEGORY_LIST){
            if (getCategoryId().toString().equals(category.getId().toString())){
                return category.findDeliveryDueDate();
            }
        }
        throw new Exception("Category could find");

    }


}
