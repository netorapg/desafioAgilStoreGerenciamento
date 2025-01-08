package com.agilstore.cli;

import com.agilstore.model.Product;
import com.agilstore.repository.ProductRepo;
import com.agilstore.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private ProductService productService;
    private Scanner scanner;

    public CLI(ProductService productService2) {
        // Inicialize o ProductRepo e o ProductService corretamente
        ProductRepo productRepo = new ProductRepo();
        this.productService = new ProductService(productRepo);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("1. Adicionar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Atualizar produto");
            System.out.println("4. Excluir produto");
            System.out.println("5. Buscar produtos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProducts();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void addProduct() {
        String name;
        while (true) {
            System.out.print("Digite o nome do produto: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break; // Sai do loop se o nome não estiver vazio
            } else {
                System.out.println("O nome do produto não pode ser vazio. Por favor, insira um nome válido.");
            }
        }
    
        int quantity = 0;
        while (true) {
            System.out.print("Digite a quantidade em estoque: ");
            String quantityInput = scanner.nextLine();
            try {
                quantity = Integer.parseInt(quantityInput);
                break; // Sai do loop se a entrada for válida
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Por favor, insira um número inteiro.");
            }
        }

        String category;
        while (true) {
            System.out.print("Digite a categoria do produto: ");
            category = scanner.nextLine().trim();
            if (!category.isEmpty()) {
                break; // Sai do loop se a categoria não estiver vazia
            } else {
                System.out.println("A categoria do produto não pode ser vazia. Por favor, insira uma categoria válida.");
            }
        }
    
        double price = 0.0;
        while (true) {
            System.out.print("Digite o preço unitário do produto: ");
            String priceInput = scanner.nextLine().replace(",", ".");
            try {
                price = Double.parseDouble(priceInput);
                break; // Sai do loop se a entrada for válida
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido. Por favor, insira um número decimal.");
            }
        }
    
        String description;
        while (true) {
            System.out.print("Digite a descrição do produto: ");
            description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                break; // Sai do loop se a descrição não estiver vazia
            } else {
                System.out.println("A descrição do produto não pode ser vazia. Por favor, insira uma descrição válida.");
            }
        }
    
        int id = productService.generateNewId();
        Product product = new Product(id, name, quantity,category, price, description);
        productService.addProduct(product);
        System.out.println("Produto adicionado com sucesso!");
    }
    
    
    private void listProducts() {
        System.out.println("Lista de produtos:");
    
        // Opção de filtrar por categoria
        System.out.print("Deseja filtrar por categoria? (S/N): ");
        String filterChoice = scanner.nextLine().trim().toUpperCase();
    
        List<Product> filteredProducts;
        if (filterChoice.equals("S")) {
            System.out.print("Digite a categoria para filtrar: ");
            String category = scanner.nextLine().trim();
            filteredProducts = productService.getAllProducts().stream()
                    .filter(product -> product.getCategory().equalsIgnoreCase(category))
                    .toList();
        } else {
            filteredProducts = productService.getAllProducts();
        }
    
        // Exibe os produtos em formato de tabela
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Nome", "Categoria", "Quantidade", "Preço");
        System.out.println("---------------------------------------------------------------");
        filteredProducts.forEach(product -> {
            System.out.printf("%-5d %-20s %-15s %-10d %-10.2f%n", 
                    product.getId(), 
                    product.getName(), 
                    product.getCategory(), 
                    product.getQuantity(), 
                    product.getPrice());
        });
    
        if (filteredProducts.isEmpty()) {
            System.out.println("Nenhum produto encontrado para a categoria especificada.");
        }
    }
    

    private void updateProduct() {
        System.out.print("Digite o ID do produto que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
    
        try {
            Product existingProduct = productService.getProductById(id);
            System.out.println("Produto encontrado: " + existingProduct.getName());
    
            System.out.print("Digite o novo nome do produto (deixe em branco para manter o nome atual): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                if (name.length() < 3) {
                    System.out.println("Erro: O nome do produto deve ter pelo menos 3 caracteres.");
                    return;
                }
                existingProduct.setName(name);
            }
    
            System.out.print("Digite a nova quantidade do produto (deixe em branco para manter a quantidade atual): ");
            String quantityInput = scanner.nextLine();
            if (!quantityInput.isEmpty()) {
                try {
                    int newQuantity = Integer.parseInt(quantityInput);
                    if (newQuantity < 0) {
                        System.out.println("Erro: A quantidade não pode ser negativa.");
                        return;
                    }
                    existingProduct.setQuantity(newQuantity);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Quantidade inválida. Deve ser um número inteiro.");
                    return;
                }
            }
    
            System.out.print("Digite a nova categoria do produto (deixe em branco para manter a categoria atual): ");
            String category = scanner.nextLine();
            if (!category.isEmpty()) {
                if (category.length() < 3) {
                    System.out.println("Erro: A categoria deve ter pelo menos 3 caracteres.");
                    return;
                }
                existingProduct.setCategory(category);
            }
    
            System.out.print("Digite o novo preço do produto (deixe em branco para manter o preço atual): ");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(priceInput.replace(",", "."));
                    if (newPrice < 0) {
                        System.out.println("Erro: O preço não pode ser negativo.");
                        return;
                    }
                    existingProduct.setPrice(newPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Preço inválido. Deve ser um número decimal, usando vírgula ou ponto.");
                    return;
                }
            }
    
            System.out.print("Digite a nova descrição do produto (deixe em branco para manter a descrição atual): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                if (description.length() < 5) {
                    System.out.println("Erro: A descrição deve ter pelo menos 5 caracteres.");
                    return;
                }
                existingProduct.setDescription(description);
            }
    
            productService.updateProduct(existingProduct);
            System.out.println("Produto atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro ao atualizar o produto: " + e.getMessage());
        }
    }
    
    
    
    private void deleteProduct() {
        System.out.print("Digite o ID do produto que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
    
        try {
            Product product = productService.getProductById(id);
            System.out.println("Produto encontrado: ");
            System.out.println("ID: " + product.getId());
            System.out.println("Nome: " + product.getName());
            System.out.println("Categoria: " + product.getCategory());
            System.out.println("Quantidade: " + product.getQuantity());
            System.out.println("Preço: " + product.getPrice());
            System.out.println("Descrição: " + product.getDescription());
    
            System.out.print("Tem certeza que deseja excluir este produto? (s/n): ");
            String confirmation = scanner.nextLine();
    
            if (confirmation.equalsIgnoreCase("s")) {
                productService.removeProductById(id);
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    

    private void searchProducts() {
        System.out.println("Buscar produto:");
        System.out.print("Digite o nome do produto (ou deixe vazio para ignorar): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? null : name;

        System.out.print("Digite o ID do produto (ou deixe vazio para ignorar): ");
        String idInput = scanner.nextLine();
        Integer id = null;
        if (!idInput.isEmpty()) {
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Por favor, insira um número inteiro.");
                return;
            }
        }

        List<Product> results = productService.searchProducts(name, id);
        System.out.println("\nResultados da Busca:");
        if (results.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            results.forEach(product -> {
                System.out.println("ID: " + product.getId() + ", Nome: " + product.getName() +
                        ", Preço: " + product.getPrice() + ", Quantidade: " + product.getQuantity() + "Category: " + product.getCategory() +
                        ", Descrição: " + product.getDescription());
            });
        }
    }

}