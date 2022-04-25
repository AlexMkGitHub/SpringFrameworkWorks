package my.homework.controller;

import my.homework.product_preset.Product;
import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecifications {

    public static Specification<Product> titleContaining(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> costContaining(long id) {
        return (root, query, cb) -> cb.like(root.get("id"), id + "%");
    }
}
