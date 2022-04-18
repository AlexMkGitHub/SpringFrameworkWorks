package my.homework.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ByerDao {
    private final EntityManagerFactory emFactory;

    public ByerDao(EntityManagerFactory emFactory) {
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

}
