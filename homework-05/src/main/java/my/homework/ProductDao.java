package my.homework;

import my.homework.model.Product;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private EntityManagerFactory emFactory;
    private EntityManager em;

    public ProductDao() {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        em = emFactory.createEntityManager();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
    }

    public void saveOrUpdate(Product product) {
        em.getTransaction().begin();
        if (product.getId() == null) {
            em.merge(product);
        } else {
            em.persist(product);
        }
        em.getTransaction().commit();

//        Session session = em.unwrap(Session.class);
//        session.saveOrUpdate(product);
    }

    public void closeConnection() {
        em.close();
        emFactory.close();
    }

}
