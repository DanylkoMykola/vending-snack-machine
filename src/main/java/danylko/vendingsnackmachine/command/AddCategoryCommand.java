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
        String[] strArr = arguments.split("\"");
        String[] numArr;
        String category;
        double price;
        int amount = 0;
        if (strArr.length == 3) {
            category = strArr[1];
            numArr = strArr[2].trim().split(" ");
            if (numArr.length != 0 && numArr.length <= 2) {
                try {
                        price = Double.parseDouble(numArr[0]);
                    if (numArr.length == 2)
                        amount = Integer.parseInt(numArr[1]);
                    product = new Product(category.replace("\"", ""), price, amount);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter correct format of price and amount");
                    e.printStackTrace();
                }
            }
        }
        return product;
    }
}
