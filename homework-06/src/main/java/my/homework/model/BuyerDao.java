package my.homework.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class BuyerDao {
    private final EntityManagerFactory emFactory;

    public BuyerDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Buyer findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Buyer buyer = em.find(Buyer.class, id);
        emFactory.close();
        return buyer;
    }

    public List<Buyer> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Buyer> buyerList = em.createQuery("select b from Buyer b", Buyer.class).getResultList();
        emFactory.close();
        return buyerList;
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Buyer buyer = em.find(Buyer.class, id);
        em.remove(buyer);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Buyer buyer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            if (buyer.getId() == null) {
                em.merge(buyer);
            } else {
                em.persist(buyer);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public List<Buyer> findProductsBuyer(long buyerId) {
        EntityManager em = emFactory.createEntityManager();
        List<Buyer> prodBuyers = em.createQuery("SELECT b FROM Buyer b, Product p WHERE b.id = p.buyer.id and b.id=:buyerId", Buyer.class)
                .setParameter("buyerId", buyerId)
                .getResultList();
        em.close();
        return prodBuyers;
    }

    public List<ListAll> listBuyersProducts() {
        EntityManager em = emFactory.createEntityManager();
        List<Object[]> listAll = em.createQuery("select b.id, b.name, p.id, p.title, p.price from Buyer b, Product p ", Object[].class).getResultList();
        em.close();
        List<ListAll> mediaList = new ArrayList<>();
        for (Object[] objects : listAll) {
            ListAll data = new ListAll();
            data.setBuyerId((long) objects[0]);
            data.setBuyerName((String) objects[1]);
            data.setProductId((long) objects[2]);
            data.setProductTittle((String) objects[3]);
            data.setProductPrice((int) objects[4]);
            mediaList.add(data);
        }
        return mediaList;
    }

}

