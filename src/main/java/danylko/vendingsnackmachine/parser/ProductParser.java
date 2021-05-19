package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.ProductParseException;
import org.springframework.stereotype.Component;

@Component
public interface ProductParser {
    String parseCategory(String args) throws ProductParseException;
    double parsePrice(String args) throws ProductParseException;
    int parseAmount(String args) throws ProductParseException;
    boolean isAmountPresent(String args);
}
