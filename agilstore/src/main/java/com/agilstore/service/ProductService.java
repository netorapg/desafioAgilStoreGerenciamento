package com.agilstore.service;

import com.agilstore.model.Product;
import com.agilstore.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepo productRepo;
    private int currentMaxId;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
        this.currentMaxId = productRepo.getMaxProductId();
    }

    public void addProduct(Product product) {
        productRepo.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepo.getProductById(id);
        return product.orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado."));
    }

    public void updateProduct(Product updatedProduct) {
        boolean updated = productRepo.updateProduct(updatedProduct);
        if (!updated) {
            throw new RuntimeException("Não foi possível atualizar o produto. Produto com ID " + updatedProduct.getId() + " não encontrado.");
        }
    }

    public void removeProductById(int id) {
        boolean removed = productRepo.removeProductById(id);
        if (!removed) {
            throw new RuntimeException("Não foi possível remover o produto. Produto com ID " + id + " não encontrado.");
        }
    }

    public int generateNewId() {
        return ++currentMaxId;
    }
}
