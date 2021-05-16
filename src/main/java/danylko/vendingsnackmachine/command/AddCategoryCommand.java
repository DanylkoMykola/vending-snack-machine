package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
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
    public void execute(String arguments) {
        Product product = parseProduct(arguments);
        Product productFromDB = productService.create(product);
        if (productFromDB == null) {
            System.out.println("Incorrect input. Try again or input \"help\"!");
        }
        else {
            System.out.format("%s %.2f %d", product.getCategory(), product.getPrice(), product.getAmount());
        }
    }

    private Product parseProduct(String arguments) {
        Product product = null;
        String[] argsArr = arguments.split(" ");
        StringBuilder category = new StringBuilder();
        for (int i = 1; i < argsArr.length - 2; i++ ) {
            category.append(argsArr[i]).append(" ");
        }
        try {
            double price = Double.parseDouble(argsArr[argsArr.length-2]);
            int amount = Integer.parseInt(argsArr[argsArr.length-1]);
            product = new Product(category.toString().replace("\"", ""), price, amount);
        } catch (NumberFormatException e) {
            System.out.println("Please enter correct format of price and amount");
        }
        return product;
    }
}
