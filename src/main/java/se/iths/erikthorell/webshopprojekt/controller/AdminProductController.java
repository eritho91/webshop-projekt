package se.iths.erikthorell.webshopprojekt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.erikthorell.webshopprojekt.service.ProductService;

@Controller
@RequestMapping("/admin/products")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }
}
