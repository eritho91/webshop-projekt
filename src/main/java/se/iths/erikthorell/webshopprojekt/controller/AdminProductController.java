package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.service.CategoryService;
import se.iths.erikthorell.webshopprojekt.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService,
                                  CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createProduct(@Valid @ModelAttribute Product product) {

        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);

        productService.createProduct(product);

        return "redirect:/products";
    }

    @GetMapping
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "admin";
    }
}

