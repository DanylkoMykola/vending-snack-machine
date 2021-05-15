package danylko.vendingsnackmachine.repo;

import danylko.vendingsnackmachine.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
