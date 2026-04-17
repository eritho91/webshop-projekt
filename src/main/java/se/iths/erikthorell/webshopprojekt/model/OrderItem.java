package se.iths.erikthorell.webshopprojekt.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;

    private BigDecimal priceAtPurchase;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity, BigDecimal priceAtPurchase) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }
}