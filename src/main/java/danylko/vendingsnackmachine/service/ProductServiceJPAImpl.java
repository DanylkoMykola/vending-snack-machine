package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        Product productFromDB = repository.findProductByCategory(product.getCategory());
        if (productFromDB == null){
            return repository.save(product);
        }
        if (productFromDB.getDeleteAt() != null)  {
            productFromDB.setDeleteAt(null);
            return repository.save(productFromDB);
        }
        return productFromDB;
    }


    @Override
    public Product update(Product product) {
        if (product == null) {
            return null;
        }
        Product productFromDB = repository.findProductByCategory(product.getCategory());
        if (productFromDB == null || productFromDB.getDeleteAt() != null){
            return null;
        }
        productFromDB.setAmount(product.getAmount());
        return repository.save(productFromDB);
    }
    @Override
    public Product addAmount(Product product) {
        if (product == null) {
            return null;
        }
        Product productFromDB = repository.findProductByCategory(product.getCategory());
        if (productFromDB == null || productFromDB.getDeleteAt() != null){
            return null;
        }
        productFromDB.setAmount(product.getAmount() + productFromDB.getAmount());
        return repository.save(productFromDB);
    }

    @Override
    @Transactional
    public List<Product> deleteEmptyCategories() {
        List<Product> products = repository.findAllByAmount(0);
        products.forEach(product -> product.setDeleteAt(LocalDate.now()));
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<Product> iterable = repository.findAllByDeleteAtIsNull();
        iterable.forEach(products::add);
        return products;
    }

    @Override
    public Product findByCategory(String category) {
        if (category == null || category.isEmpty()){
            return null;
        }
        return repository.findProductByCategory(category);
    }
}
