package se.iths.erikthorell.webshopprojekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Positive
    private BigDecimal price;

    @Min(0)
    private int stock;

    @Size(max = 1000)
    private String description;

    private String imageUrl;

    public Product() {
    }

    public Product(String name, Category category, BigDecimal price, int stock, String description, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
