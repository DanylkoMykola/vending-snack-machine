package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.parser.ProductParser;
import danylko.vendingsnackmachine.service.ProductService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddItemCommandTest {

    Command command;
    private Product product;
    private Product productFromDB;
    private String correctStr;
    private String withoutQuotesStr;
    private String withoutAmountStr;
    private String category;
    private int amount;

    @Mock
    private ProductService service;

    @Mock
    private ProductParser parser;

    @BeforeEach
    void init() {
        command = new AddItemCommand(service, parser);
        product = new Product("Sweets", null, 5);
        productFromDB = new Product(1L, "Sweets", 55.55, 5);
        correctStr = "addItem \"Sweets\" 5";
        withoutQuotesStr = "addItem Sweets 5";
        withoutAmountStr = "addItem \"Sweets\"";
        category = "Sweets";
        amount = 5;
    }
    @Test
    void shouldAddAmountToExistProduct() throws ProductParseException {
        when(parser.parseCategory(correctStr)).thenReturn(category);
        when(parser.parseAmount(correctStr)).thenReturn(amount);
        when(service.addAmount(product)).thenReturn(productFromDB);

        command.execute(correctStr);

        verify(parser, times(1)).parseCategory(correctStr);
        verify(parser, times(1)).parseAmount(correctStr);
        verify(service, times(1)).addAmount(product);
    }

    @Test
    void shouldThrowException() throws ProductParseException {
        when(parser.parseCategory(withoutQuotesStr)).thenThrow(ProductParseException.class);
        command.execute(withoutQuotesStr);

        verify(parser, times(1)).parseCategory(withoutQuotesStr);
        verify(service, times(0)).addAmount(product);
    }

}