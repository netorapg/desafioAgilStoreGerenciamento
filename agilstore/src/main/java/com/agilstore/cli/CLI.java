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
        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();
        System.out.print("Digite a quantidade em estoque: ");
        int quantity = scanner.nextInt();
        System.out.print("Digite o preço unitário do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite a descrição do produto: ");
        String description = scanner.nextLine();

        int id = productService.generateNewId();
        Product product = new Product(id, name, quantity, price, description);
        productService.addProduct(product);
        System.out.println("Produto adicionado com sucesso!");
    }

    private void listProducts() {
        System.out.println("Lista de produtos:");
        productService.getAllProducts().forEach(product -> {
            System.out.println(
                    "ID: " + product.getId() + ", Nome: " + product.getName() + ", Preço: " + product.getPrice()
                            + ", Quantidade: " + product.getQuantity() + ", Descrição: " + product.getDescription());
        });
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
                existingProduct.setName(name);
            }

            System.out.print("Digite a nova quantidade do produto (deixe em branco para manter a quantidade atual): ");
            String quantityInput = scanner.nextLine();
            if (!quantityInput.isEmpty()) {
                existingProduct.setQuantity(Integer.parseInt(quantityInput));
            }

            System.out.print("Digite o novo preço do produto (deixe em branco para manter o preço atual): ");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()) {
                existingProduct.setPrice(Double.parseDouble(priceInput));
            }

            System.out.print("Digite a nova descrição do produto (deixe em branco para manter a descrição atual): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                existingProduct.setDescription(description);
            }

            productService.updateProduct(existingProduct);
            System.out.println("Produto atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.print("Digite o ID do produto que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        try {
            productService.removeProductById(id);
            System.out.println("Produto excluído com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
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
                        ", Preço: " + product.getPrice() + ", Quantidade: " + product.getQuantity() +
                        ", Descrição: " + product.getDescription());
            });
        }
    }

}