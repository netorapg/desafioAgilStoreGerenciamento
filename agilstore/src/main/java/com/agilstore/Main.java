package com.agilstore;

import com.agilstore.repository.ProductRepo;
import com.agilstore.service.ProductService;
import com.agilstore.cli.CLI;

public class Main {
    public static void main(String[] args) {
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);

        CLI cli = new CLI(productService);
        cli.run();
    }
}