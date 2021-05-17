package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.PurchaseParseException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PurchaseParser {

    private final static DateTimeFormatter FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d");
    private final static DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");


    public static YearMonth parseReportYearMonth(String args) throws PurchaseParseException {
        YearMonth yearMonth;
        int startIndex = args.lastIndexOf(" ");
        try {
            yearMonth = YearMonth.parse(args.substring(startIndex + 1), YEAR_MONTH_FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new PurchaseParseException("Failed to parse date. Make sure that you input the correct values!", e);
        }
        return yearMonth;
    }


    public static LocalDate parseDate(String args) throws PurchaseParseException {
        LocalDate date;
        int startIndex = args.lastIndexOf(" ");
        try {
            date = LocalDate.parse(args.substring(startIndex + 1), FULL_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new PurchaseParseException("Failed to parse date. Make sure that you input the correct values!", e);
        }
        return date;
    }
}
