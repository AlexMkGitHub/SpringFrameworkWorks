package my.homework.service;

import my.homework.controller.ProductSpecifications;
import my.homework.dto.ProductDto;
import my.homework.product_preset.Product;
import my.homework.product_preset.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Page<ProductDto> findProductByFilter(String titleFilter, BigDecimal priceFilter, Integer page, Integer size, String sortField, int sortValue) {
        Specification<Product> spec = Specification.where(null);
        if (titleFilter != null) {
            spec = spec.and(ProductSpecifications.titleContaining(titleFilter));
        }
        if (priceFilter != null) {
            spec = spec.and(ProductSpecifications.priceContaining(priceFilter));
        }
        if (sortValue == 0) {
            return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                    .map(ProductServiceImpl::productToDto);
        } else {
            return productRepository.findAll(spec, PageRequest.of(page, size, Sort.Direction.DESC, sortField))
                    .map(ProductServiceImpl::productToDto);
        }
    }


    @Override
    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id).map(ProductServiceImpl::productToDto);
    }


    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(productRepository.save(
                new Product(
                        product.getId(),
                        product.getTitle(),
                        product.getPrice()
                )));
    }


    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto productToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice()
        );
    }
}
