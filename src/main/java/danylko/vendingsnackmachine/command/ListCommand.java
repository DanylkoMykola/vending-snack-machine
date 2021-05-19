package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCommand implements Command{
    public static final String COMMAND_NAME = "list";
    private final ProductService productService;

    public ListCommand(ProductService productService) {
        this.productService = productService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }

    @Override
    public void execute(String args) {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            ConsoleWriter.println(ConsoleHandler.EMPTY_LIST);
        }
        else {
            for (Product  product : products) {
                ConsoleWriter.println(product.toString() + " " + product.getAmount());
            }
        }

    }
}
