package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.webshopprojekt.model.Cart;
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
}
