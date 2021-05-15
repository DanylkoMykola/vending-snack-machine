package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PurchaseService {
    Purchase save(Purchase purchase);
    Map<Product, Integer> getReportByDate(String date);
}
