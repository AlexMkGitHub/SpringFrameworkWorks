package my.homework.controller;

import my.homework.dto.ProductDto;
import my.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public String listPage(@RequestParam Optional<String> productTitleFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           @RequestParam Optional<Integer> sortValue,
                           Model model) {
        String titleFilterValue = productTitleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        Integer sortValueParm = sortValue.orElse(0);
        model.addAttribute("products", productService.findProductByFilter(titleFilterValue, pageValue, sizeValue, sortFieldValue, sortValueParm));
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
