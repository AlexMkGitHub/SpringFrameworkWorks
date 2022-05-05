package my.homework.product.product_controller;

import my.homework.product.product_preset.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecifications {

    public static Specification<Product> titleContaining(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> priceContaining(BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

}
