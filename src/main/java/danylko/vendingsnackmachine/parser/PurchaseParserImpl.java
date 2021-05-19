package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.exception.PurchaseParseException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class PurchaseParserImpl implements PurchaseParser {

    private final static DateTimeFormatter FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d");
    private final static DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");


    public YearMonth parseReportYearMonth(String args) throws PurchaseParseException {
        YearMonth yearMonth;
        int startIndex = args.lastIndexOf(" ");
        try {
            yearMonth = YearMonth.parse(args.substring(startIndex + 1), YEAR_MONTH_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new PurchaseParseException(ConsoleHandler.DATE_EXCEPTION, e);
        }
        return yearMonth;
    }


    public LocalDate parseDate(String args) throws PurchaseParseException {
        LocalDate date;
        int startIndex = args.lastIndexOf(" ");
        try {
            date = LocalDate.parse(args.substring(startIndex + 1), FULL_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new PurchaseParseException(ConsoleHandler.DATE_EXCEPTION, e);
        }
        return date;
    }
    public boolean isYearMonthFormat(String args) {
        String[] argsArr = args.split(" ");
        if (argsArr.length == 2) {
            return argsArr[1].length() == 7;
        }
        return false;
    }
}
