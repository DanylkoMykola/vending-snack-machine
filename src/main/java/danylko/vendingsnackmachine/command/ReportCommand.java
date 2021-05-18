package danylko.vendingsnackmachine.command;

import danylko.vendingsnackmachine.console.ConsoleWriter;
import danylko.vendingsnackmachine.console.handler.ConsoleHandler;
import danylko.vendingsnackmachine.entity.Purchase;
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
        Map<Purchase, Integer> report = null;
        LocalDate date;
        YearMonth yearMonth;
        boolean isExist = true;
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
            isExist = false;
            ConsoleWriter.write(e.getMessage());
        }
        if (report != null && !report.isEmpty()) {
            for (Map.Entry<Purchase, Integer> pair : report.entrySet()) {
                Purchase purchase = pair.getKey();
                int amount = pair.getValue();
                total += purchase.getPrice() * amount;
                ConsoleWriter.write(purchase.toString() + " " + amount);
            }
            DecimalFormat priceFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));
            ConsoleWriter.write("Total " + priceFormat.format(total));
        }
        else{
            if (isExist)
            ConsoleWriter.write(ConsoleHandler.REPORT_EMPTY);
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
