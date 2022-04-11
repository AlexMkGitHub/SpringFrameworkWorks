package my.homework.controller;

import my.homework.product_preset.Product;
import my.homework.product_preset.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
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

    @GetMapping("/delete/{id}")
    public String productFormDelete(@PathVariable("id") long id) {
        productRepository.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", productRepository.save(new Product("", 0)));
        return "product_form";
    }
}
