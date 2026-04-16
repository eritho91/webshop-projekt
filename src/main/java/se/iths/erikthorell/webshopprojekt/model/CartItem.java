package se.iths.erikthorell.webshopprojekt.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {

    private Product product;

    // antal av produkter som lagts till
    private int quantity;


    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // räknar ut priset för raden
    public BigDecimal getTotalPrice() {
        // produktens pris och multiplicerar med antalet
        // BigDecimal.valueOf(quantity) omvandlar siffran till rätt format för uträkningen
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
