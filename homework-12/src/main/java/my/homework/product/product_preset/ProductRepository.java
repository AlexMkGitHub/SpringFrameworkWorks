package my.homework.product.product_preset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findProductByTitleLike(@Param("title") String title);

    List<Product> findProductByIdLike(@Param("id") long id);

    List<Product> findProductByPriceLike(@Param("price") BigDecimal price);

    @Query("select p " +
            " from Product p " +
            "where (p.title like concat('%', :title, '%') or :title is null) and " +
            "      (p.price = :price or :price is null)")
    List<Product> findUserByFilter(@Param("title") String title,
                                   @Param("price") BigDecimal price);
}
