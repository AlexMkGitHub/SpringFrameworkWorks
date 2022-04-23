package my.homework.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")


public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private int price;

    @ManyToMany
    private List<Buyer> buyer;

    public Product() {
    }

    public Product(String title, int price, List<Buyer> buyer) {
        this.title = title;
        this.price = price;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Buyer> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<Buyer> buyer) {
        this.buyer = buyer;
    }
}

