package com.agilstore.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.agilstore.model.Product;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private static final String FILE_PATH = "products.json";
    private List<Product> products;
    private ObjectMapper objectMapper;

    public ProductRepo() {
        this.objectMapper = new ObjectMapper();
        this.products = loadProducts();
    }

    private List<Product> loadProducts() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveProducts() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxProductId() {
        return products.stream().mapToInt(Product::getId).max().orElse(0);
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public boolean updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == updatedProduct.getId()) {
                products.set(i, updatedProduct);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public boolean removeProductById(int id) {
        boolean removed = products.removeIf(product -> product.getId() == id);
        if (removed) {
            saveProducts();
        }
        return removed;
    }

    public List<Product> searchProducts(String name, Integer id) {
        return products.stream()
                .filter(product -> (name == null || product.getName().toLowerCase().contains(name.toLowerCase())) &&
                        (id == null || product.getId() == id))
                .toList();
    }
}
