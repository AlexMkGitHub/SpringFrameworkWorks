package my.homework.model;

public class ListAll {
    private long buyerId;
    private String buyerName;
    private long productId;
    private String productTittle;
    private int productPrice;

    public ListAll() {
    }

    public ListAll(long buyerId, String buyerName, long productId, String productTittle, int productPrice) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.productId = productId;
        this.productTittle = productTittle;
        this.productPrice = productPrice;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductTittle() {
        return productTittle;
    }

    public void setProductTittle(String productTittle) {
        this.productTittle = productTittle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ListAll{" +
                "buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", productId=" + productId +
                ", productTittle='" + productTittle + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
