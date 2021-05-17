package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import danylko.vendingsnackmachine.exception.ProductParseException;
import danylko.vendingsnackmachine.exception.PurchaseParseException;
import danylko.vendingsnackmachine.parser.ProductParser;
import danylko.vendingsnackmachine.parser.PurchaseParser;
import danylko.vendingsnackmachine.service.ProductService;
import danylko.vendingsnackmachine.service.PurchaseService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PurchaseCommand implements Command {

    public static final String COMMAND_NAME = "purchase";
    private final ProductService productService;
    private final PurchaseService purchaseService;

    public PurchaseCommand(ProductService productService, PurchaseService purchaseService) {
        this.productService = productService;
        this.purchaseService = purchaseService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }
    @Override
    public void execute(String args) {
        Product product = null;
        Purchase purchase;
        LocalDate date = null;
        Product productFromDB;
        try {
            String category = ProductParser.parseCategory(args);
            date = PurchaseParser.parseDate(args);
            product = new Product(category, null, -1);
        } catch (ProductParseException | PurchaseParseException e) {
            System.out.println(e.getMessage());
        }
        productFromDB = productService.update(product);
        if (productFromDB != null && date != null) {
            purchase = purchaseService.save( new Purchase(productFromDB, date));
            System.out.println(purchase.toString());
        }
        else {
            System.out.println("There is no existing category that you are trying to purchase!");
        }
    }
}
