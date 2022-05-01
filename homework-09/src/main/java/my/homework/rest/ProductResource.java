package my.homework.rest;

import my.homework.controller.NotFoundException;
import my.homework.dto.ErrorDto;
import my.homework.dto.ProductDto;
import my.homework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;
    private ErrorDto errorDto;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
        this.errorDto = new ErrorDto();
    }

    @GetMapping("/all")
    private Page<ProductDto> findAll(@RequestParam Optional<String> productTitleFilter,
                                     @RequestParam Optional<BigDecimal> productPriceFilter,
                                     @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> sortField,
                                     @RequestParam Optional<Integer> sortValue) {
        String titleFilterValue = productTitleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        BigDecimal priceFilterValue = productPriceFilter.orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        Integer sortValueParam = sortValue.orElse(0);

        return productService.findProductByFilter(titleFilterValue, priceFilterValue, pageValue, sizeValue, sortFieldValue, sortValueParam);
    }

    @GetMapping("/{id}/id")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new NotFoundException("Product not found!"));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return productService.save(product);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Created product should have id");
        }
        return productService.save(product);
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String sqlException(SQLException ex) {
        return ex.getMessage();
    }




}
