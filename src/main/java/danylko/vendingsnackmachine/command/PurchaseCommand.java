package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.parser.ProductParser;

public class PurchaseCommand implements Command {
    @Override
    public void execute(String args) {
        Product product = null;
        try {
            String category = ProductParser.parseCategory(args);
        } catch (ProductParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
