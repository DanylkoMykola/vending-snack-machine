package danylko.vendingsnackmachine.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Product implements Serializable, Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private Double price;

    @Column
    private Integer amount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Product(String category, Double price, Integer amount) {
        this.category = category;
        this.price = price;
        this.amount = amount;
    }

    public Product(String category, Double price) {
        this.category = category;
        this.price = price;
    }

    public Product() {
    }

    @Override
    public int compareTo(Product o) {
        return this.category.compareTo(o.category);
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
