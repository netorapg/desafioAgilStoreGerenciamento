package com.agilstore.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.agilstore.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private static final String FILE_PATH = "products.json"; // Caminho para o arquivo JSON
    private List<Product> products;
    private ObjectMapper objectMapper;

    public ProductRepo() {
        this.objectMapper = new ObjectMapper();
        this.products = loadProducts();  // Carregar produtos do arquivo ao inicializar
    }

    // Carregar os produtos do arquivo JSON
    private List<Product> loadProducts() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Salvar os produtos no arquivo JSON
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

    // Adicionar um produto
    public void addProduct(Product product) {
        products.add(product);
        saveProducts(); // Salva após adicionar
    }

    // Obter todos os produtos
    public List<Product> getAllProducts() {
        return products;
    }

    // Encontrar um produto pelo ID, retorna Optional<Product>
    public Optional<Product> getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    // Atualizar um produto
    public boolean updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == updatedProduct.getId()) {
                products.set(i, updatedProduct);
                saveProducts(); // Salva após atualizar
                return true;
            }
        }
        return false;
    }

    // Remover um produto
    public boolean removeProductById(int id) {
        boolean removed = products.removeIf(product -> product.getId() == id);
        if (removed) {
            saveProducts(); // Salva após remover
        }
        return removed;
    }
}
