package danylko.vendingsnackmachine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
}
