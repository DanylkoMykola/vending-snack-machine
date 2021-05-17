package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.parser.ProductParser;
import danylko.vendingsnackmachine.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class AddCategoryCommand implements Command{

    public static final String COMMAND_NAME = "addCategory";

    private final ProductService productService;

    public AddCategoryCommand(ProductService productService) {
        this.productService = productService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }

    @Override
    public void execute(String args) {
        Product product = null;
        try {
            String category = ProductParser.parseCategory(args);
            double price = ProductParser.parsePrice(args);
            if (isAmountPresent(args)) {
                int amount = ProductParser.parseAmount(args);
                product = new Product(category, price, amount);
            }
            else {
                product = new Product(category, price);
            }

        } catch (ProductParseException e) {
            System.out.println(e.getMessage());
        }
        Product productFromDB = productService.create(product);
        if (productFromDB != null) {
            System.out.println(productFromDB.toString());
        }
    }
    private boolean isAmountPresent(String args) {
        String[] argsArr = args.split("\"");
        if (argsArr.length == 3) {
            String[] tempArr = argsArr[2].trim().split(" ");
            return tempArr.length == 2;
        }
        return false;
    }
}
