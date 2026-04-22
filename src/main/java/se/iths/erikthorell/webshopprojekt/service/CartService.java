package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.CartItem;
import se.iths.erikthorell.webshopprojekt.model.Product;

@Service
public class CartService {


    // hämtar/skapar kundvagn
    public Cart getOrCreateCart(HttpSession session) {
        // kollar om cart finns
        Cart cart = (Cart) session.getAttribute("cart");

        // om cart inte finns
        if (cart == null) {
            // skapar vi en ny tom kundvagn
            cart = new Cart();
            // sparar vagnen i sessionen för framtiden
            session.setAttribute("cart", cart);
        }
        return cart;

    }


    // lägger till produkt i kundvagnen
    public void addProductToCart(Product product, HttpSession session) {
        // skapar vagn
        Cart cart = getOrCreateCart(session);

        // lägger till product
        cart.addProduct(product);
    }


    // tömmer kundvagn
    public void clearCart(HttpSession session) {
        // hämtar vagn från sessionen
        Cart cart = (Cart) session.getAttribute("cart");

        // om vagnen finns, töm den på produkter
        if (cart != null) {
            cart.clear();
        }
    }

    // uppdatera antal +1 -1
    public void updateQuantity(HttpSession session, Long id, int amount) {
        Cart cart = getOrCreateCart(session);

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(id)) {
                int newQuantity = item.getQuantity() + amount;

                // 0 eller mindre så tas produkten bort
                if (newQuantity <= 0) {
                    cart.getItems().remove(item);
                } else {
                    item.setQuantity(newQuantity);
                }
                return;
            }
        }
    }

    // tar bort en produkt i kundvagnen
    public void removeItem(HttpSession session, Long id) {
        Cart cart = getOrCreateCart(session);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(id));
    }
}