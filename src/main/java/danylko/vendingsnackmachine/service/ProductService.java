package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    List<Product> deleteEmptyCategories();

}
