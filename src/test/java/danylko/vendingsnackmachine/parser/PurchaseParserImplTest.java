package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.PurchaseParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseParserImplTest {

    private final DateTimeFormatter FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d");
    private final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    private PurchaseParserImpl parser;
    private String purchase;
    private String purchaseIncorrectDate;
    private String reportFullDate;
    private String reportYearMonth;
    private String reportIncorrectInput;

    @BeforeEach
    void init() {
        parser = new PurchaseParserImpl();
        purchase = "purchase \"Sweets\" 2021-04-12";
        purchaseIncorrectDate = "purchase \"Sweets\" 2021-4-12";
        reportFullDate = "report 2021-04-12";
        reportYearMonth = "report 2021-04";
        reportIncorrectInput = "report 2021-";
    }
    @Test
    void parseReportYearMonth() throws PurchaseParseException {
        YearMonth date = YearMonth.parse("2021-04", YEAR_MONTH_FORMATTER);
        assertEquals(parser.parseReportYearMonth(reportYearMonth), date);
        assertThrows(PurchaseParseException.class,
                () -> parser.parseReportYearMonth(reportIncorrectInput));
        assertThrows(PurchaseParseException.class,
                () -> parser.parseReportYearMonth(""));
    }

    @Test
    void parseDate() throws PurchaseParseException {
        LocalDate date = LocalDate.parse("2021-04-12", FULL_DATE_FORMATTER);
        assertEquals(parser.parseDate(purchase), date);
        assertThrows(PurchaseParseException.class,
                () -> parser.parseDate(purchaseIncorrectDate));
        assertThrows(PurchaseParseException.class,
                () -> parser.parseDate(""));
    }

    @Test
    void isYearMonthFormat() {
        assertTrue(parser.isYearMonthFormat(reportYearMonth));
        assertFalse(parser.isYearMonthFormat(reportIncorrectInput));
        assertFalse(parser.isYearMonthFormat(reportFullDate));
    }
}