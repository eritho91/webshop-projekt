package se.iths.erikthorell.webshopprojekt.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Cart {


    // Lista för kundvagnen
    private List<CartItem> items = new ArrayList<>();


    public void addProduct(Product product) {
        for (CartItem item : items) {

            //jämför ID på produkten i vagnen med produkt ID vi vill lägga till
            if (item.getProduct().getId().equals(product.getId())) {
                // om varan finns +1
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        // finns inte varan läggs +1 ny produkt till
        items.add(new CartItem(product, 1));

    }

    // returnera hela listan med varor så vi kan visa dem på HTML sidan
    public List<CartItem> getItems() {
        return items;
    }

    // tömmer kundvagnen när vi handlat klart
    public void clear() {
        items.clear();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO; // startar på 0

        for (CartItem item : items) {
            // totalen på varje rad
            total = total.add(item.getTotalPrice());
        }

        return total;
    }
}


//    public BigDecimal getTotalCartPrice() {
//        return items
//                .stream() // kollar igenom kundvagnen
//                .mapToDouble(CartItem::getTotalPrice) // omvandlar varorna till siffror
//                .sum(); // plusar ihop alla varor och ger totala summan
//    }
//}