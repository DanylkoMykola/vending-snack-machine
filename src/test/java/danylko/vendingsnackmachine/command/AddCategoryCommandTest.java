package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.parser.ProductParser;
import danylko.vendingsnackmachine.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddCategoryCommandTest {

    private Command command;
    private Product product;
    private Product productFromDB;
    private String correctStr;
    private String incorrectStr;
    private String category;
    private double price;
    private int amount;

    @Mock
    private ProductService service;

    @Mock
    private ProductParser parser;

    @BeforeEach
    void init() {
        command = new AddCategoryCommand(service, parser);
        product = new Product("Sweets", 55.55, 5);
        productFromDB = new Product(1L, "Sweets", 55.55, 5);
        correctStr = "addCategory \"Sweets\" 55.55 5";
        incorrectStr = "addCategory \"Swee 55.55";
        category = "Sweets";
        price = 55.55;
        amount = 5;
    }
    @Test
    void shouldCreateProduct() throws ProductParseException {
        when(parser.parseCategory(correctStr)).thenReturn(category);
        when(parser.parsePrice(correctStr)).thenReturn(price);
        when(parser.parseAmount(correctStr)).thenReturn(amount);
        when(parser.isAmountPresent(correctStr)).thenReturn(true);
        when(service.create(product)).thenReturn(productFromDB);

        command.execute(correctStr);

        verify(service, times(1)).create(product);
    }
    @Test
    void shouldNotCreateProduct() throws ProductParseException {
        lenient().when(parser.parseCategory(incorrectStr)).thenThrow(ProductParseException.class);
        command.execute(correctStr);
        verify(service, times(0)).create(product);
    }
}