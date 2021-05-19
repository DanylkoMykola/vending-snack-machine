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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PurchaseCommand implements Command {

    public static final String COMMAND_NAME = "purchase";
    private final PurchaseService purchaseService;
    private final PurchaseParser purchaseParser;
    private final ProductParser productParser;

    public PurchaseCommand(PurchaseService purchaseService,
                           @Qualifier("purchaseParserImpl") PurchaseParser purchaseParser,
                           ProductParser productParser) {
        this.purchaseService = purchaseService;
        this.purchaseParser = purchaseParser;
        this.productParser = productParser;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }
    @Override
    public void execute(String args) {
        String category;
        Purchase purchase = null;
        LocalDate date = null;
        try {
            category = productParser.parseCategory(args);
            date = purchaseParser.parseDate(args);
            purchase = new Purchase(new Product(category), date);
        } catch (ProductParseException | PurchaseParseException e) {
            ConsoleWriter.println(e.getMessage());
        }
        purchase = purchaseService.save(purchase);
        if (purchase == null) {
            ConsoleWriter.println(ConsoleHandler.NOTHING_TO_BUY);
        }
        else {
            ConsoleWriter.println(purchase.toString());
        }

    }
}
