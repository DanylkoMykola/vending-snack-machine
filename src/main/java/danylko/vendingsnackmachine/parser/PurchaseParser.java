package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.PurchaseParseException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

@Component
public interface PurchaseParser {
    YearMonth parseReportYearMonth(String args) throws PurchaseParseException;
    LocalDate parseDate(String args) throws PurchaseParseException;
    boolean isYearMonthFormat(String args);
}
