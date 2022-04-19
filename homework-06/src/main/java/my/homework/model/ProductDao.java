package my.homework.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private final EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Product findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        emFactory.close();
        return product;
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> productlist = em.createQuery("select p from Product p", Product.class).getResultList();
        emFactory.close();
        return productlist;
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            if (product.getId() == null) {
                em.merge(product);
            } else {
                em.persist(product);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public List<Product> findProductBuyers(long productId) {
        EntityManager em = emFactory.createEntityManager();
        List<Product> buyProducts = em.createQuery("select p from Product p where p.buyer.id = :productId", Product.class)
                .setParameter("productId", productId)
                .getResultList();
        em.close();
        return buyProducts;

    }
}
