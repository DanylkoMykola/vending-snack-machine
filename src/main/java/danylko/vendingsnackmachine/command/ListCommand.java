package danylko.vendingsnackmachine.command;

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
        for (Product  product : products) {
            System.out.println(product.toString());
        }
    }
}
