package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
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
        String category = null;
        Purchase purchase = null;
        LocalDate date = null;
        Product productFromDB = null;
        boolean isExist = true;
        try {
            category = ProductParser.parseCategory(args);
            date = PurchaseParser.parseDate(args);
            productFromDB = productService.findByCategory(category);
        } catch (ProductParseException | PurchaseParseException e) {
            isExist = false;
            ConsoleWriter.write(e.getMessage());
        }
        if (productFromDB != null && date != null ) {
            if (productFromDB.getAmount() != 0){
                purchase = purchaseService.save( new Purchase(productFromDB.getCategory(),
                        productFromDB.getPrice(),
                        date));
            }
            if (purchase == null) {
                ConsoleWriter.write(ConsoleHandler.NOTHING_TO_BUY);
            }
            else {
                productFromDB.decrementAmount();
                productService.update(productFromDB);
                ConsoleWriter.write(purchase.toString());
            }

        }
        else if (isExist){
            ConsoleWriter.write(ConsoleHandler.NO_EXISTING_CATEGORY);
        }
    }
}
