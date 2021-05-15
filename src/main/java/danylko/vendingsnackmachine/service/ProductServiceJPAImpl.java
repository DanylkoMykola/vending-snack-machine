package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceJPAImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceJPAImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        if (product == null) {
            return null;
        }
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            return null;
        }
        Product productFromDB = repository.findProductByCategory(product.getCategory());
        if (productFromDB == null){
            return null;
        }
        productFromDB.setAmount(product.getAmount());
        return repository.save(productFromDB);
    }

    @Override
    public List<Product> deleteEmptyCategories() {
        return repository.deleteProductByAmount(0);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<Product> iterable = repository.findAll();
        iterable.forEach(products::add);
        return products;
    }
}
