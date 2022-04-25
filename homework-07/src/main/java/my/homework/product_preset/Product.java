package my.homework.product_preset;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.text.DecimalFormat;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    @Min(0)
    @Max(100000)
    private double price;

    public Product() {
    }

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
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

    public double getCost() {
        return price;
    }

    public void setCost(double cost) {
        this.price = cost;
    }

}
