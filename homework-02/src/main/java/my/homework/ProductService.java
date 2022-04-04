package my.homework;

import my.homework.product_preset.Product;
import my.homework.product_preset.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final Cart cart;
    private long productId;
    private final Scanner sc;

    public ProductService() {
        this.productRepository = new ProductRepository();
        this.cart = new Cart();
        this.sc = new Scanner(System.in);
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void addProduct() {
        productRepository.insert(new Product("Milk", 75.00, "Молоко"));
        productRepository.insert(new Product("Bread", 45.50, "Хлеб"));
        productRepository.insert(new Product("Buckwheat", 85.35, "Гречневая крупа"));
        productRepository.insert(new Product("Apple", 115.00, "Яблоко"));
        productRepository.insert(new Product("Butter", 250.70, "Масло"));
        productRepository.insert(new Product("Potatoes", 70.00, "Картошка"));
        productRepository.insert(new Product("Onion", 65.85, "Лук"));
        productRepository.insert(new Product("Carrot", 75.00, "Морковь"));
        productRepository.insert(new Product("Spaghetti", 95.20, "Спагетти"));
        productRepository.insert(new Product("Orange", 185.00, "Апельсин"));
    }

    public void listAllProduct() {
        for (int i = 0; i < productRepository.findAll().size(); i++) {
            System.out.println(
                    "Id: " + productRepository.findAll().get(i).getId() +
                            "  Продукт: " + productRepository.findAll().get(i).getRuTitle() +
                            "  Цена: " + productRepository.findAll().get(i).getCost());
        }
        System.out.println();
    }

    public void listCommand() {
        System.out.println("Список комманд: С");
        System.out.println("Каталог продуктов: L");
        System.out.println("Добавить продукт в корзину: A");
        System.out.println("Удалить продукт из корзины: D");
        System.out.println("Список продуктов в корзине: P");
        System.out.println();
    }

    public void addProductInCart() {
        System.out.print("Введите Id продукта для добавления в корзину:");
        productId = sc.nextLong();
        if (productId > productRepository.findAll().size()) {
            System.out.println("Нет продукта с таким Id!");
            return;
        }
        cart.insert(productRepository.findById(productId));
        System.out.println("Продукт " + productRepository.findById(productId).getRuTitle() + " добавлен в корзину.");
        System.out.println();
    }

    public void deleteProductFromCart() {
        if (cart.findAll().isEmpty()) {
            System.out.println("В корзине нет продуктов!");
            return;
        }
        System.out.print("Введите Id продукта для удаления из корзины:");
        productId = sc.nextLong();
        if (productId > cart.findAll().size()) {
            System.out.println("Нет в корзине продукта с таким Id!");
            return;
        }
        System.out.println("Продукт " + cart.findById(productId).getRuTitle() + " удален из корзины.");
        System.out.println();
        cart.delete(productId);
    }

    public void productInCart() {
        if (cart.findAll().isEmpty()) {
            System.out.println("Корзина пуста!");
        } else {
            System.out.println("Сейчас в корзине:");
            for (int i = 0; i < cart.findAll().size(); i++) {
                System.out.println(
                        "Id: " + cart.findAll().get(i).getId() +
                                "  Продукт: " + cart.findAll().get(i).getTitle() +
                                "  Цена: " + cart.findAll().get(i).getCost());
            }
        }
        System.out.println();
    }

}
