package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.erikthorell.webshopprojekt.model.Cart;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.service.CartService;
import se.iths.erikthorell.webshopprojekt.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // visar kundvagnssidan
    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = cartService.getOrCreateCart(session); // hämtar vagnen från session
        model.addAttribute("cart", cart); // skickar vagnen till HTML sidan

        return "cart";
    }

    // lägga till varor i vagnen
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id); // hämtar produkt via id

        Cart cart = cartService.getOrCreateCart(session); // hämtar kundens vagn
        cart.addProduct(product); // lägger till produkten i vagnen

        return "redirect:/products";
    }


}