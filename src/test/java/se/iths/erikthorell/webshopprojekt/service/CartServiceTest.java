package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private HttpSession session;


    @Test
    void createNewCart() {
        // kollar att kundvagn är tom
        when(session.getAttribute("cart")).thenReturn(null);
        //anropar metoden
        Cart results = cartService.getOrCreateCart(session);
        //kontrollerar att ny vagn skapas
        assertNotNull(results);
        //verifierar att vagnen sparas i sessionen
        verify(session).setAttribute(eq("cart"), any(Cart.class));


    }

    @Test
    void returnExistingCart() {
        // skapar vagn
        Cart existing = new Cart();
        when(session.getAttribute("cart")).thenReturn(existing);
        //anropar metoden
        Cart results = cartService.getOrCreateCart(session);
        // kontrollerar resultat
        assertEquals(existing, results);

    }

    @Test
    void addProductToCart() {
        //skapar vagn o produkt
        Cart cart = new Cart();
        Product product = new Product();
        product.setName("Testprodukt");

        when(session.getAttribute("cart")).thenReturn(cart);

        //testar metoden
        cartService.addProductToCart(product, session);

        //kollar så produkten hamnar i vagnen
        assertEquals(1, cart.getItems().size());
        assertEquals("Testprodukt", cart.getItems().get(0).getProduct().getName());
    }

    @Test
    void clearCart() {
        Cart cart = new Cart();
        cart.addProduct(new Product());

        when(session.getAttribute("cart")).thenReturn(cart);

        cartService.clearCart(session);

        assertEquals(0, cart.getItems().size());

    }
}