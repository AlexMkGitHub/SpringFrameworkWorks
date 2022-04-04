package my.homework;

public class Product {

    private Long id;
    private String title;
    private double cost;
    private String ruTitle;

    public Product(String title, double cost, String ruTitle) {
        this.title = title;
        this.cost = cost;
        this.ruTitle = ruTitle;
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
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getRuTitle() {
        return ruTitle;
    }

    public void setRuTitle(String ruTitle) {
        this.ruTitle = ruTitle;
    }
}
