package danylko.vendingsnackmachine.command;

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
        boolean isValid = true;
        Product product = null;
        Product productFromDB;
        try {
            String category = ProductParser.parseCategory(args);
            int amount = ProductParser.parseAmount(args);
            product = new Product(category, null, amount);
        } catch (ProductParseException e) {
            isValid = false;
            System.out.println(e.getMessage());
        }
        productFromDB = productService.update(product);
        if (productFromDB != null) {
            System.out.println(productFromDB.toString() + " " + productFromDB.getAmount());
        }
        else if (isValid){
            System.out.println("There is no existing category that you are trying to add!");
        }
    }
}
