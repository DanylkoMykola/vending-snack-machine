package danylko.vendingsnackmachine.console.handler;

public interface ConsoleHandler {
    String ADVICE = " Make sure that you input the correct values!";
    String NOTHING_TO_TEMP = "There is nothing to ";
    String CATEGORY_EXCEPTION = "No category to parse. Make sure that category put in quotes!";
    String PRICE_EXCEPTION = "No price to parse!";
    String PRICE_PARSE_EXCEPTION = "Failed to parse price!" + ADVICE;
    String AMOUNT_EXCEPTION = "No amount to parse!";
    String AMOUNT_PARSE_EXCEPTION = "Failed to parse amount!" + ADVICE;
    String DATE_EXCEPTION = "Failed to parse date. Example: purchase|report yyyy-MM-dd | report yyyy-MM";
    String START_LINE = "\nPlease start inputting your command:" +
            "\n\tIf you need help enter \"help\";" +
            "\n\tTo close app enter \"exit\".";
    String NO_EXISTING_COMMAND = "Enter correct command:";
    String NO_EXISTING_CATEGORY = "There is no existing category or available product!";
    String HELP = "Available commands: \n\texit";
    String EMPTY_LIST = "The list of products is empty!";
    String CLEAR_EMPTY = NOTHING_TO_TEMP + "clear!";
    String GREATER_THAN_ZERO = "Price and amount must be grater than zero";
    String REPORT_EMPTY = NOTHING_TO_TEMP + "report!";
    String NOTHING_TO_BUY = NOTHING_TO_TEMP + "buy";
}
