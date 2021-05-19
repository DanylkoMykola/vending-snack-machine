package danylko.vendingsnackmachine.repo;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findPurchaseByDate(LocalDate date);
    List<Purchase> findPurchasesByDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
