package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    List<Product> delete();
    List<Product> list(LocalDate date);
}
