package com.agilstore.service;

import com.agilstore.model.Product;
import com.agilstore.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/* 
class ProductServiceTest {

    private ProductRepo productRepo;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepo = mock(ProductRepo.class);
        productService = new ProductService(productRepo);
    }

    @Test
    void addProduct_shouldAddProduct() {
        Product product = new Product(1, "Product A", 10, 99.99, "Description");

        productService.addProduct(product);

        verify(productRepo, times(1)).addProduct(product);
    }

    @Test
    void getAllProducts_shouldReturnAllProducts() {
        List<Product> products = List.of(
                new Product(1, "Product A", 10, 99.99, "Description"),
                new Product(2, "Product B", 5, 49.99, "Description")
        );
        when(productRepo.getAllProducts()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product A", result.get(0).getName());
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product(1, "Product A", 10, 99.99, "Description");
        when(productRepo.getProductById(1)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals("Product A", result.getName());
    }

    @Test
    void getProductById_shouldThrowExceptionWhenNotFound() {
        when(productRepo.getProductById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1);
        });

        assertEquals("Produto com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void updateProduct_shouldUpdateProduct() {
        Product updatedProduct = new Product(1, "Updated Product", 20, 199.99, "Updated Description");
        when(productRepo.updateProduct(updatedProduct)).thenReturn(true);

        productService.updateProduct(updatedProduct);

        verify(productRepo, times(1)).updateProduct(updatedProduct);
    }

    @Test
    void updateProduct_shouldThrowExceptionWhenNotFound() {
        Product updatedProduct = new Product(1, "Updated Product", 20, 199.99, "Updated Description");
        when(productRepo.updateProduct(updatedProduct)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(updatedProduct);
        });

        assertEquals("Não foi possível atualizar o produto. Produto com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void removeProductById_shouldRemoveProduct() {
        when(productRepo.removeProductById(1)).thenReturn(true);

        productService.removeProductById(1);

        verify(productRepo, times(1)).removeProductById(1);
    }

    @Test
    void removeProductById_shouldThrowExceptionWhenNotFound() {
        when(productRepo.removeProductById(1)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.removeProductById(1);
        });

        assertEquals("Não foi possível remover o produto. Produto com ID 1 não encontrado.", exception.getMessage());
    }
}
*/