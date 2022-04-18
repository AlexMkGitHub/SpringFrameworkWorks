package my.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "buyers")
@NamedQueries({
        @NamedQuery(name = "findAllBuyers", query = "from Buyer"),
        @NamedQuery(name = "countAllBuyers", query = "select count(b) from Buyer b"),
        @NamedQuery(name = "deleteBuyers", query = "delete from Buyer where id = :id")
})
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Product product;

    public Buyer() {
    }

    public Buyer(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
