package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class CartDBTest {

    @Autowired
    private CartService cartService;

    private MockHttpSession session = new MockHttpSession();

    @MockitoBean
    private JavaMailSender javaMailSender;

    @Test
    void createCart() {
        Cart cart = cartService.getOrCreateCart(session);

        assertNotNull(cart);

        assertNotNull(session.getAttribute("cart"));
    }

    @Test
    void addProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setName("mat");

        cartService.addProductToCart(p, session);

        Cart cart = (Cart) session.getAttribute("cart");

        assertEquals(1, cart.getItems().size());
        assertEquals("mat", cart.getItems().get(0).getProduct().getName());
    }


    @Test
    void clearCart() {
        Product p = new Product();
        cartService.addProductToCart(p, session);

        cartService.clearCart(session);

        Cart cart = (Cart) session.getAttribute("cart");

        assertEquals(0, cart.getItems().size());
    }
}
