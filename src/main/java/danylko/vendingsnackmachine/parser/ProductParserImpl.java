package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.exception.ProductParseException;
import org.springframework.stereotype.Component;

@Component
public class ProductParserImpl implements ProductParser{

    public String parseCategory(String args) throws ProductParseException {
        if (!checkValidString(args)) {
            throw new ProductParseException(ConsoleHandler.CATEGORY_EXCEPTION);
        }
        int startIndex = args.indexOf("\"");
        int endIndex = args.indexOf("\"", startIndex +1);
        return args.substring(startIndex + 1, endIndex);
    }

    public double parsePrice(String args) throws ProductParseException {
        if (!checkValidString(args)) {
            throw new ProductParseException(ConsoleHandler.PRICE_EXCEPTION);
        }
        int startIndex = args.lastIndexOf("\"");
        String temp = args.substring(startIndex+1).trim();
        double price;
        try {
            if (temp.contains(" ")) {
                price = Double.parseDouble(temp.substring(0, temp.indexOf(" ")));
            }
            else {
                price = Double.parseDouble(temp);
            }
        } catch (NumberFormatException e) {
            throw new ProductParseException(ConsoleHandler.PRICE_PARSE_EXCEPTION, e);
        }
        if (!isGraterThanZero(price)) {
            throw new ProductParseException(ConsoleHandler.GREATER_THAN_ZERO);
        } else {
            return price;
        }
    }

    public int parseAmount(String args) throws ProductParseException {
        if (!checkValidString(args)) {
            throw new ProductParseException(ConsoleHandler.AMOUNT_EXCEPTION);
        }
        int startIndex = args.lastIndexOf(" ");
        int amount;
        try {
            amount = Integer.parseInt(args.substring(startIndex + 1));
        } catch (NumberFormatException e) {
            throw new ProductParseException(ConsoleHandler.AMOUNT_PARSE_EXCEPTION, e);
        }
        if (!isGraterThanZero(amount)) {
            throw new ProductParseException(ConsoleHandler.GREATER_THAN_ZERO);
        } else {
            return amount;
        }
    }
    private boolean checkValidString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches(".+\".+\".*");
    }
    public boolean isAmountPresent(String args) {
        String[] argsArr = args.split("\"");
        if (argsArr.length == 3) {
            String[] tempArr = argsArr[2].trim().split(" ");
            return tempArr.length == 2;
        }
        return false;
    }
    private boolean isGraterThanZero(double val) {
        return val > 0.0;
    }
    private boolean isGraterThanZero(int val) {
        return val > 0;
    }
}
