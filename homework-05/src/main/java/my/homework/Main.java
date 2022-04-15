package my.homework;

import my.homework.model.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(new Product("Milk", 75));
        em.persist(new Product("Bread", 45));
        em.persist(new Product("Buckwheat", 85));
        em.persist(new Product("Apple", 115));
        em.persist(new Product("Butter", 250));
        em.persist(new Product("Potatoes", 70));
        em.persist(new Product("Onion", 65));
        em.persist(new Product("Carrot", 75));
        em.persist(new Product("Spaghetti", 95));
        em.persist(new Product("Orange", 185));

        em.getTransaction().commit();

        em.close();
        emFactory.close();
    }
}
