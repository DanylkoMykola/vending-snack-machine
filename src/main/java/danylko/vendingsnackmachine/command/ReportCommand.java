package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.PurchaseParseException;
import danylko.vendingsnackmachine.parser.PurchaseParser;
import danylko.vendingsnackmachine.service.PurchaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Map;

@Component
public class ReportCommand implements Command {

    public static final String COMMAND_NAME = "report";
    private final PurchaseService purchaseService;
    private final PurchaseParser parser;

    public ReportCommand(PurchaseService purchaseService,
                         @Qualifier("purchaseParserImpl") PurchaseParser parser) {
        this.purchaseService = purchaseService;
        this.parser = parser;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }

    @Override
    public void execute(String args) {
        Map<Product, Integer> report = null;
        LocalDate date;
        YearMonth yearMonth;
        boolean isExist = true;
        double total = 0.0;
        try {
            if (parser.isYearMonthFormat(args)) {
                yearMonth = parser.parseReportYearMonth(args);
                report = purchaseService.getReportByYearMonth(yearMonth);
            }
            else {
                date = parser.parseDate(args);
                report = purchaseService.getReportByDate(date);
            }
        } catch (PurchaseParseException e) {
            isExist = false;
            ConsoleWriter.println(e.getMessage());
        }
        if (report != null && !report.isEmpty()) {
            for (Map.Entry<Product, Integer> pair : report.entrySet()) {
                Product product = pair.getKey();
                int amount = pair.getValue();
                total += product.getPrice() * amount;
                ConsoleWriter.println(product.toString() + " " + amount);
            }
            DecimalFormat priceFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));
            ConsoleWriter.println("Total " + priceFormat.format(total));
        }
        else{
            if (isExist)
            ConsoleWriter.println(ConsoleHandler.REPORT_EMPTY);
        }

    }
}
