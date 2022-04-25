package my.homework.controller;

import my.homework.dto.ProductDto;
import my.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String listPage(@RequestParam Optional<String> productTitleFilter, Optional<Long> productIdFilter,
                           Model model) {
        String titleFilterValue = productTitleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        model.addAttribute("products", productService.findProductByFilter(titleFilterValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String productForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("product") ProductDto product, BindingResult binding) {
        if (binding.hasErrors() || product.getTitle().isEmpty() || product.getCost() == null) {
            return "product_form";
        } else {
            productService.save(product);
        }
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }
}
