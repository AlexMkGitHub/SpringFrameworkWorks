package my.homework.service;

import my.homework.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findProductByFilter(String titleFilter, BigDecimal priceFilterValue, Integer page, Integer size, String sortField, int sortValue);

    Optional<ProductDto> findById(long id);

    ProductDto save(ProductDto product);

    void deleteById(long id);

}
