package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Purchase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface PurchaseService {
    Purchase save(Purchase purchase);
    Map<Purchase, Integer> getReportByDate(LocalDate date);
}
