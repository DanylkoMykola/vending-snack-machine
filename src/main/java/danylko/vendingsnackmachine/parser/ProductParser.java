package danylko.vendingsnackmachine.parser;

import danylko.vendingsnackmachine.exception.ProductParseException;

public class ProductParser {

    public static String parseCategory(String args) throws ProductParseException {
        if (!checkValidString(args)) {
            throw new ProductParseException("No category to parse. Make sure that category put in quotes!");
        }
        int startIndex = args.indexOf("\"");
        int endIndex = args.indexOf("\"", startIndex +1);
        return args.substring(startIndex + 1, endIndex);
    }

    public static double parsePrice(String args) throws ProductParseException{
        if (!checkValidString(args)) {
            throw new ProductParseException("No price to parse!");
        }
        int startIndex = args.lastIndexOf("\"");
        String temp = args.substring(startIndex+1).trim();
        try {
            if (temp.contains(" ")) {
                return Double.parseDouble(temp.substring(0, temp.indexOf(" ")));
            }
            else {
                return Double.parseDouble(temp);
            }
        } catch (NumberFormatException e) {
            throw new ProductParseException("Failed to parse price. Make sure that you input the correct values!", e);
        }
    }

    public static int parseAmount(String args) throws ProductParseException {
        if (!checkValidString(args)) {
            throw new ProductParseException("No amount to parse!");
        }
        String trimArgs = args.trim();
        int startIndex = trimArgs.lastIndexOf(" ");
        try {
            return Integer.parseInt(trimArgs.substring(startIndex + 1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ProductParseException("Failed to parse amount. Make sure that you input the correct values!", e);
        }
    }
    public static boolean checkValidString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.contains("\"");
    }
}
