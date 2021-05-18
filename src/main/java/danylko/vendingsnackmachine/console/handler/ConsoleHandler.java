package danylko.vendingsnackmachine.console.handler;

public interface ConsoleHandler {
    String ADVICE = "  Make sure that you input the correct values!";
    String CATEGORY_EXCEPTION = "No category to parse. Make sure that category put in quotes!";
    String PRICE_EXCEPTION = "No price to parse!";
    String PRICE_PARSE_EXCEPTION = "Failed to parse price." + ADVICE;
    String AMOUNT_EXCEPTION = "No amount to parse!";
    String AMOUNT_PARSE_EXCEPTION = "Failed to parse amount." + ADVICE;
    String DATE_EXCEPTION = "Failed to parse date." + ADVICE;
    void write(String str);
}
