package se.iths.erikthorell.webshopprojekt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // sparar tidpunkt efter checkout
    private LocalDateTime orderDate;

    // totala summan för hela köpet
    private BigDecimal totalAmount;


    private String userEmail;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  // en order har många rader
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();


    public Order() {
        this.orderDate = LocalDateTime.now();
    }


}