package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.parser.ProductParser;
import danylko.vendingsnackmachine.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class AddItemCommand implements Command{

    public static final String COMMAND_NAME = "addItem";
    private final ProductService productService;

    public AddItemCommand(ProductService productService) {
        this.productService = productService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }
    @Override
    public void execute(String args) {
        boolean isExist = true;
        Product product = null;
        Product productFromDB;
        try {
            String category = ProductParser.parseCategory(args);
            int amount = ProductParser.parseAmount(args);
            product = new Product(category, null, amount);
        } catch (ProductParseException e) {
            isExist = false;
            System.out.println(e.getMessage());
        }
        productFromDB = productService.update(product);
        if (productFromDB != null) {
            ConsoleWriter.write(productFromDB.toString() + " " + productFromDB.getAmount());
        }
        else if (isExist){
            ConsoleWriter.write(ConsoleHandler.NO_EXISTING_CATEGORY);
        }
    }
}
