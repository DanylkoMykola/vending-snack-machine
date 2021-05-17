package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.PurchaseParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PurchaseParser {

    public static LocalDate parseDate(String args) throws PurchaseParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = null;
        int startIndex = args.lastIndexOf(" ");
        try {
            date = LocalDate.parse(args.substring(startIndex), dateFormatter);
        } catch (DateTimeParseException e) {
            throw new PurchaseParseException("Failed to parse date. Make sure that you input the correct values!", e);
        }
        return date;
    }
}
