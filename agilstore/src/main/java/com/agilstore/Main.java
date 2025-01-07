package com.agilstore;

import com.agilstore.repository.ProductRepo;
import com.agilstore.service.ProductService;
import com.agilstore.cli.CLI;

public class Main {
    public static void main(String[] args) {
        // Criando o repositório e o serviço
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);

        // Criando e executando a interface CLI
        CLI cli = new CLI(productService);
        cli.run();
    }
}
