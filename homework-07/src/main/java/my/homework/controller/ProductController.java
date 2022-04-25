package my.homework.controller;

import my.homework.product_preset.Product;
import my.homework.product_preset.ProductRepository;
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

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public String listPage(@RequestParam Optional<String> productTitleFilter, Optional<Long> productIdFilter,
                           Model model) {
        if (productTitleFilter.isEmpty() && productIdFilter.isEmpty()) {
            model.addAttribute("products", productRepository.findAll());
        } else {
            if (productTitleFilter.isPresent() && productIdFilter.isEmpty()) {
                model.addAttribute("products", productRepository.findProductByTitleLike("%" + productTitleFilter.get() + "%"));
            } else {
                model.addAttribute("products", productRepository.findProductByTitleLike("%" + productTitleFilter.get() + "%"));
                model.addAttribute("products", productRepository.findProductByIdLike(productIdFilter.get()));
            }
        }
        return "product";
    }

    @GetMapping("/{id}")
    public String productForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping
    public String productSave(@Valid Product product, BindingResult binding) {
        if (binding.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", productRepository.save(new Product("", 0)));
        return "product_form";
    }
}
