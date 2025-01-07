package com.agilstore.repository;

import com.agilstore.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private final List<Product> products = new ArrayList<>();

    
    public void addProduct(Product product) {
        products.add(product);
    }

    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    
    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    
    public boolean updateProduct(Product updatedProduct) {
        Optional<Product> existingProductOpt = getProductById(updatedProduct.getId());
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return true;
        }
        return false;
    }


    public boolean removeProductById(int id) {
        return products.removeIf(product -> product.getId() == id);
    }
}
