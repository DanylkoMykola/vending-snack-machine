package danylko.vendingsnackmachine.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
    private Integer amount = 0;

    @Column
    private LocalDate deleteAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Purchase> purchases;

    public Product(Long id, String category, Double price, Integer amount) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.amount = amount;
    }

    public Product(String category, Double price, Integer amount) {
        this.category = category;
        this.price = price;
        this.amount = amount;
    }

    public Product(String category, Double price) {
        this.category = category;
        this.price = price;
    }

    public Product(String category) {
        this.category = category;
    }

    public Product() {
    }

    @Override
    public int compareTo(Product o) {
        int compareRes = this.category.compareTo(o.category);
        if (compareRes == 0) {
            return (int) (Math.round(this.price) - Math.round(o.price));
        }
        return compareRes;
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

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }

    public void decrementAmount() {
        if (this.amount > 0) {
            this.amount--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(category, product.category)) return false;
        return Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        DecimalFormat priceFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));
        return category + " " + priceFormat.format(price);
    }

}
