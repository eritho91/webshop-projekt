package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.service.CategoryService;
import se.iths.erikthorell.webshopprojekt.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showProducts(Model model, Authentication auth) {
        List<Product> products = productService.getProducts(auth);

        model.addAttribute("products",
                productService.groupByCategory(products));

        return "products";
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/category/{category}")
    public Product getProductByCategory(@PathVariable Category category) {
        return productService.getProductByCategory(category);
    }

    @GetMapping("/price/{price}")
    public Product getProductByPrice(@PathVariable BigDecimal price) {
        return productService.getProductByPrice(price);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createProduct(@Valid @ModelAttribute Product product) {

        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);

        productService.createProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "create-product";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
