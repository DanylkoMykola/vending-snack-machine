package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import danylko.vendingsnackmachine.repo.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository repository;

    public PurchaseServiceImpl(PurchaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return repository.save(purchase);
    }

    @Override
    public Map<Product, Integer> getReportByDate(String date) {
        Map<Product, Integer> report = new TreeMap<>();
        List<Purchase> purchases = findAllByDate(date);

        for (Purchase purchase : purchases) {
            report.computeIfPresent(purchase.getProduct(), (key, val) -> val++);
            report.putIfAbsent(purchase.getProduct(), 0);
        }
         return report;
    }

    private List<Purchase>  findAllByDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        List<Purchase> purchases;
        try{
            if (date.trim().length() > 7) {
                LocalDate localDate = LocalDate.parse(date, dateFormatter);
                purchases = repository.findPurchaseByDate(localDate);
            }
            else {
                LocalDate localDateMonth = LocalDate.parse(date, dateMonthFormatter);
                LocalDate from = localDateMonth.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate to = localDateMonth.with(TemporalAdjusters.firstDayOfMonth());
                purchases = repository.findPurchasesByDateBetween(from, to);
            }
        } catch (DateTimeParseException e) {
            return new ArrayList<>();
        }
        return purchases;
    }
}
