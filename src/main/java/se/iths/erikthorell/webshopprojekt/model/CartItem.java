package se.iths.erikthorell.webshopprojekt.model;

import lombok.Getter;
import lombok.Setter;
import se.iths.erikthorell.webshopprojekt.entity.Product;

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
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

