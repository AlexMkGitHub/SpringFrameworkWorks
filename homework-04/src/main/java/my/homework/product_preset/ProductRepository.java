package my.homework.product_preset;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new Product("Milk", 75.00));
        this.insert(new Product("Bread", 45.50));
        this.insert(new Product("Buckwheat", 85.35));
        this.insert(new Product("Apple", 115.00));
        this.insert(new Product("Butter", 250.70));
        this.insert(new Product("Potatoes", 70.00));
        this.insert(new Product("Onion", 65.85));
        this.insert(new Product("Carrot", 75.00));
        this.insert(new Product("Spaghetti", 95.20));
        this.insert(new Product("Orange", 185.00));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }

}
