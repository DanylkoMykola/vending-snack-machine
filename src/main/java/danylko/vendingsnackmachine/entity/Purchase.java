package danylko.vendingsnackmachine.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Purchase implements Serializable, Comparable<Purchase> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private Double price;

    @Column
    private LocalDate date;

    public Purchase() {
    }

    public Purchase(String category, Double price, LocalDate date) {
        this.category = category;
        this.price = price;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int compareTo(Purchase o) {
        return this.category.compareTo(o.category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (!Objects.equals(category, purchase.category)) return false;
        return Objects.equals(price, purchase.price);
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
