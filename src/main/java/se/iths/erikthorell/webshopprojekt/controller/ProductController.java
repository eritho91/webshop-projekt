package se.iths.erikthorell.webshopprojekt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.erikthorell.webshopprojekt.model.Product;
import se.iths.erikthorell.webshopprojekt.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model, Authentication auth) {
        List<Product> products = productService.getProducts(auth);

        model.addAttribute("products",
                productService.groupByCategory(products));

        return "products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

// Old unused code for getting products by name, category and price. Kept for future reference if needed.

//    @GetMapping("/name/{name}")
//    public Product getProductByName(@PathVariable String name) {
//        return productService.getProductByName(name);
//    }
//
//    @GetMapping("/category/{category}")
//    public Product getProductByCategory(@PathVariable Category category) {
//        return productService.getProductByCategory(category);
//    }
//
//    @GetMapping("/price/{price}")
//    public Product getProductByPrice(@PathVariable BigDecimal price) {
//        return productService.getProductByPrice(price);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id,
//                                 @Valid @RequestBody Product product) {
//        return productService.updateProduct(id, product);
//    }
}
