package danylko.vendingsnackmachine.repo;

import danylko.vendingsnackmachine.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductByCategory(String category);
    List<Product> deleteProductByAmount(Integer amount);
    List<Product> findAllByDeleteAtIsNull();
    List<Product> findAllByAmount(Integer i);
}
