package se.iths.erikthorell.webshopprojekt.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    // räknar ut totala summan för hela kundvagnen
    public BigDecimal getTotalCartPrice() {
        BigDecimal total = BigDecimal.ZERO; // börjar på 0
        // går igenom listan
        for (CartItem item : items) {
            // räknar ihop total summan
            total = total.add(item.getTotalPrice());
        }
        return total;
    }
}