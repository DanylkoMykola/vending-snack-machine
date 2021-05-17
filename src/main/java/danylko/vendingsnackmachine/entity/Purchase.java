package danylko.vendingsnackmachine.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

@Entity
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;

    @Column
    private LocalDate date;

    public Purchase() {
    }

    public Purchase(Product product, LocalDate date) {
        this.product = product;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        DecimalFormat priceFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.ENGLISH));
        return date + "\n" + product.getCategory() + " " +  priceFormat.format(product.getPrice());
    }
}
