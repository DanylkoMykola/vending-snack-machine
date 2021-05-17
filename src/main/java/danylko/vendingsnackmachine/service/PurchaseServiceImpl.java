package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import danylko.vendingsnackmachine.repo.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
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
    public Map<Product, Integer> getReportByDate(LocalDate date) {
        List<Purchase> purchases = repository.findPurchaseByDate(date);
        return getReport(purchases);
    }
    @Override
    public Map<Product, Integer> getReportByYearMonth(YearMonth yearMonth) {
        List<Purchase> purchases = repository.findPurchasesByDateBetween(
                yearMonth.atDay(1),
                yearMonth.atEndOfMonth());
        return getReport(purchases);
    }
    private Map<Product, Integer> getReport(List<Purchase> purchases) {
        Map<Product, Integer> report = new TreeMap<>();
        for (Purchase purchase : purchases) {
            report.computeIfPresent(purchase.getProduct(), (key, val) -> val + 1);
            report.putIfAbsent(purchase.getProduct(), 1);
        }
        return report;
    }


}
