package my.homework.product.product_controller;

import my.homework.product.product_dto.ErrorDto;
import my.homework.product.product_dto.ProductDto;
import my.homework.product.product_service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductService productService;
    private ErrorDto ed;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> productTitleFilter,
                           @RequestParam Optional<BigDecimal> productPriceFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           @RequestParam Optional<Integer> sortValue,
                           Model model) {
        String titleFilterValue = productTitleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        BigDecimal priceFilterValue = productPriceFilter.orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(5);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        Integer sortValueParam = sortValue.orElse(0);
        model.addAttribute("products", productService.findProductByFilter(titleFilterValue, priceFilterValue, pageValue, sizeValue, sortFieldValue, sortValueParam));
        return "product";
    }

    @GetMapping("/{id}")
    public String productForm(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found!")));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("product") ProductDto product, BindingResult binding) {
        if (binding.hasErrors() || product.getTitle().isEmpty() || product.getPrice() == null) {
            binding.rejectValue("title", "", "Enter all data");
            return "product_form";
        }
        productService.save(product);
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
    public String notFoundException(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }


}
