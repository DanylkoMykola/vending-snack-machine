package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.exception.PurchaseParseException;
import danylko.vendingsnackmachine.parser.PurchaseParser;
import danylko.vendingsnackmachine.service.PurchaseService;
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

    public ReportCommand(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        CommandHandler.commands.put(COMMAND_NAME, this);
    }

    @Override
    public void execute(String args) {
        Map<Product, Integer> report = null;
        LocalDate date;
        YearMonth yearMonth;
        double total = 0.0;
        try {
            if (isYearMonthFormat(args)) {
                yearMonth = PurchaseParser.parseReportYearMonth(args);
                report = purchaseService.getReportByYearMonth(yearMonth);
            }
            else {
                date = PurchaseParser.parseDate(args);
                report = purchaseService.getReportByDate(date);
            }
        } catch (PurchaseParseException e) {
            e.printStackTrace();
        }
        if (report != null) {
            for (Map.Entry<Product, Integer> pair : report.entrySet()) {
                Product product = pair.getKey();
                int amount = pair.getValue();
                total += product.getPrice() * amount;
                System.out.println(product.toString() + " " + amount);
            }
            DecimalFormat priceFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));
            System.out.println(">" + "Total " + priceFormat.format(total));
        }

    }
    private boolean isYearMonthFormat(String args) {
        String[] argsArr = args.split(" ");
        if (argsArr.length == 2) {
            return argsArr[1].length() < 8;
        }
        return false;
    }
}
