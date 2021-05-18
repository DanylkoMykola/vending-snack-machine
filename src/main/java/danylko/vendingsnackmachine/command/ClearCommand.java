package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClearCommand implements Command {

    public static final String COMMAND_NAME = "clear";
    private final ProductService productService;

    public ClearCommand(ProductService productService) {
        this.productService = productService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }

    @Override
    public void execute(String args) {
        List<Product> products = productService.deleteEmptyCategories();
        if (products.isEmpty())
            ConsoleWriter.write(ConsoleHandler.CLEAR_EMPTY);
        else{
            for (Product product : products) {
                ConsoleWriter.write(product.toString());
            }
        }

    }
}
