package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.ProductParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductParserImplTest {

    private String addCategory;
    private String addCategoryWithoutQuotes;
    private String addCategoryWithoutAmount;
    private String addItem;
    private String addItemWithoutAmount;
    private ProductParserImpl parser;

    @BeforeEach
    void init() {
        addCategory = "addCategory \"Sweets\" 55 2";
        addCategoryWithoutQuotes = "addCategory Sweets 55 2";
        addCategoryWithoutAmount = "addCategory \"Sweets\" 55";
        addItem = "addItem \"Sweets\" 3";
        addItemWithoutAmount = "addItem \"Sweets\" ";
        parser = new ProductParserImpl();
    }
    @Test
    void parseCategory() throws ProductParseException {
        assertEquals(parser.parseCategory(addCategory), "Sweets");
        assertThrows(ProductParseException.class,
                () -> parser.parseCategory(addCategoryWithoutQuotes));
        assertThrows(ProductParseException.class,
                () -> parser.parseCategory(""));
    }

    @Test
    void parsePrice() throws ProductParseException {
        assertEquals(parser.parsePrice(addCategory), 55.0);
        assertEquals(parser.parsePrice(addCategoryWithoutAmount), 55.0);
        assertThrows(ProductParseException.class,
                () -> parser.parsePrice(addCategoryWithoutQuotes));
        assertThrows(ProductParseException.class,
                () -> parser.parsePrice(""));
    }

    @Test
    void parseAmount() throws ProductParseException {
        assertEquals(parser.parseAmount(addCategory), 2);
        assertEquals(parser.parseAmount(addItem), 3);
        assertThrows(ProductParseException.class,
                () -> parser.parseAmount(addCategoryWithoutQuotes));
        assertThrows(ProductParseException.class,
                () -> parser.parsePrice(addItemWithoutAmount));
        assertThrows(ProductParseException.class,
                () -> parser.parsePrice(""));
    }

    @Test
    void isAmountPresent() {
        assertTrue(parser.isAmountPresent(addCategory));
        assertFalse(parser.isAmountPresent(addCategoryWithoutQuotes));
        assertFalse(parser.isAmountPresent(addCategoryWithoutAmount));
        assertFalse(parser.isAmountPresent(""));
    }
}