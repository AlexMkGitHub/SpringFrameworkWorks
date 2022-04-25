package my.homework.product_preset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByTitleLike(@Param("title") String title);

    List<Product> findProductByIdLike(@Param("id") Long id);
}
