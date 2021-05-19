package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.repo.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceJPAImplTest {

    private ProductService productService;
    private Product product;
    private Product productFromDB;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void init() {
        product = new Product("Sweets", 20.55, 12);
        productFromDB = new Product(1L,"Sweets", 20.55, 12);
        productService = new ProductServiceJPAImpl(productRepository);
    }

    @Test
    void shouldCreateNewProduct() {
        when(productRepository.findProductByCategory(product.getCategory())).thenReturn(null);
        when(productRepository.save(product)).thenReturn(productFromDB);
        Product productTest = productService.create(product);
        verify(productRepository, times(1)).save(product);
        verify(productRepository, times(1)).findProductByCategory(product.getCategory());
        assertNotNull(productTest.getId());
    }

    @Test
    void shouldReturnNullFromCreateMethodNew() {
        Product productTest = productService.create(null);
        verify(productRepository, times(0)).save(product);
        verify(productRepository, times(0)).findProductByCategory(null);
        assertNull(productTest);

    }
    @Test
    void shouldUpdateProduct() {
        Product productUpdate = new Product(1L,"Sweets", 20.55, 11);
        when(productRepository.findProductByCategory(product.getCategory())).thenReturn(productFromDB);
        when(productRepository.save(productFromDB)).thenReturn(productUpdate);
        Product productTest = productService.update(product);
        verify(productRepository, times(1)).save(productFromDB);
        verify(productRepository, times(1)).findProductByCategory(product.getCategory());
        assertEquals(productTest.getAmount(), productUpdate.getAmount());
    }

    @Test
    void shouldAddAmount() {
        Product productUpdate = new Product(1L,"Sweets", 41.10, 12);
        when(productRepository.findProductByCategory(product.getCategory())).thenReturn(productFromDB);
        when(productRepository.save(productFromDB)).thenReturn(productUpdate);
        Product productTest = productService.update(product);
        verify(productRepository, times(1)).save(productFromDB);
        verify(productRepository, times(1)).findProductByCategory(product.getCategory());
        assertEquals(productTest.getPrice(), productUpdate.getPrice());
    }

    @Test
    void shouldDeleteEmptyCategories() {
        Product productToDelete = new Product(1L,"Sweets", 20.55, 0);
        List<Product> products = new ArrayList<>();
        products.add(productToDelete);
        when(productRepository.findAllByAmountAndDeleteAtIsNull(0)).thenReturn(products);
        productService.deleteEmptyCategories();
        verify(productRepository, times(1)).findAllByAmountAndDeleteAtIsNull(0);
        assertEquals(products.size(), 1);
    }

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        productService.getAllProducts();
        verify(productRepository, times(1)).findAll();
        assertEquals(products.size(), 1);
    }

    @Test
    void shouldReturnProductByCategory() {
        String category = product.getCategory();
        when(productRepository.findProductByCategory(category)).thenReturn(productFromDB);
        Product productTest = productService.findByCategory(category);
        verify(productRepository, times(1)).findProductByCategory(category);
        assertEquals(productFromDB.getId(), productTest.getId());
    }
    @Test
    void shouldReturnNullFromGetByCategory() {
        Product productTest =  productService.findByCategory("");
        assertNull(productTest);
    }
}