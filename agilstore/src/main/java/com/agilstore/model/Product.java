package com.agilstore.model;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private String category;
    private double price;
    private String description;

    public Product() {
    }

    // Construtor
    public Product(int id, String name, int quantity, String category, double price, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
