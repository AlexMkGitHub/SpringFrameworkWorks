package my.homework;

import my.homework.model.Buyer;
import my.homework.model.BuyerDao;
import my.homework.model.Product;
import my.homework.model.ProductDao;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();
        Buyer buyer = new Buyer("Alex");
        buyer.getProducts().addAll(Arrays.asList(
                new Product("Bread", 45, buyer),
                new Product("Buckwheat", 85, buyer),
                new Product("Onion", 65, buyer),
                new Product("Potatoes", 70, buyer)));

        Buyer buyer2 = new Buyer("Daniel");
        buyer.getProducts().addAll(Arrays.asList(
                new Product("Milk", 75, buyer2),
                new Product("Apple", 115, buyer2),
                new Product("Orange", 185, buyer2),
                new Product("Potatoes", 70, buyer2),
                new Product("Spaghetti", 95, buyer2)));

        Buyer buyer3 = new Buyer("Maria");
        buyer.getProducts().addAll(Arrays.asList(
                new Product("Potatoes", 70, buyer3),
                new Product("Apple", 115, buyer3),
                new Product("Butter", 250, buyer3),
                new Product("Spaghetti", 95, buyer3),
                new Product("Buckwheat", 85, buyer3),
                new Product("Milk", 75, buyer3),
                new Product("Onion", 65, buyer3)));

        Buyer buyer4 = new Buyer("Maria");
        buyer.getProducts().addAll(Arrays.asList(
                new Product("Potatoes", 70, buyer4),
                new Product("Apple", 115, buyer4),
                new Product("Milk", 75, buyer4),
                new Product("Onion", 65, buyer4)));

        Buyer buyer5 = new Buyer("Goodwin");
        buyer.getProducts().addAll(Arrays.asList(
                new Product("Milk", 75, buyer5),
                new Product("Apple", 115, buyer5),
                new Product("Orange", 185, buyer5),
                new Product("Potatoes", 70, buyer5),
                new Product("Butter", 250, buyer5),
                new Product("Buckwheat", 85, buyer5),
                new Product("Spaghetti", 95, buyer5)));

        em.persist(buyer);
        em.persist(buyer2);
        em.persist(buyer3);
        em.persist(buyer4);
        em.persist(buyer5);
        em.getTransaction().commit();

        ProductDao pd = new ProductDao(emFactory);
        BuyerDao bd = new BuyerDao(emFactory);
        pd.findProductBuyers(3).forEach(System.out::println);
//        bd.findBuyProducts(2).forEach(System.out::println);

        em.close();
        emFactory.close();


    }
}
