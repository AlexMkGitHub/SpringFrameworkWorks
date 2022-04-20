package my.homework.product_preset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {

    List<Product> findProductByTitleLike(@Param("title") String title);
    List<Product> findProductByIdLike(@Param("id") Long id);
}



//@Repository
//public class ProductRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class).getResultList();
//    }
//
//    public Optional<Product> findById(long id) {
//        return Optional.ofNullable(em.find(Product.class, id));
//    }
//
//    @Transactional
//    public Product saveOrUpdate(Product product) {
//        if (product.getId() == null) {
//            em.persist(product);
//        } else {
//            em.merge(product);
//        }
//        return product;
//    }
//
//    @Transactional
//    public void delete(long id) {
//        em.createQuery("delete from Product where id = :id")
//                .setParameter("id", id)
//                .executeUpdate();
//    }
//
//}
