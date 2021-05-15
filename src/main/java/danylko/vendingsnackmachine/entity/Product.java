package danylko.vendingsnackmachine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    private Long id;

    @Column
    private String category;

    @Column
    private Double price;

    @Column
    private Integer amount;

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
}
