package danylko.vendingsnackmachine.repo;

import danylko.vendingsnackmachine.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
