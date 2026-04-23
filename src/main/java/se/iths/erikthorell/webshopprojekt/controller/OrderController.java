package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Order;
import se.iths.erikthorell.webshopprojekt.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/checkout")
    public String checkout(HttpSession session, Authentication authentication, RedirectAttributes redirectAttributes) {

        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute
                    ("loginError", "Du måste skapa ett konto och vara inloggad för att handla i shoppen");
            return "redirect:/login";
        }

        // hämta kundvagn
        Cart cart = (Cart) session.getAttribute("cart");

        // om vagnen inte finns eller är tom, så skickas kunden tillbaks till cart
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart?error=empty";
        }


        String userEmail = authentication.getName();

        // skapar ordern
        Order savedOrder = orderService.createOrder(cart, userEmail);


        cart.clear();

        // skickar ordern till nästa sida,
        //flashattribute skickar ordern säkert till bekräftelsesidan
        redirectAttributes.addFlashAttribute("order", savedOrder);

        return "redirect:/confirmation";
    }


    @GetMapping("/confirmation")
    public String confirmation() {
        return "order-confirmation";
    }
}