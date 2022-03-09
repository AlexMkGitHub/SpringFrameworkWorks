package my.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    static ProductRepository globalProdRep;
    private ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        globalProdRep = productRepository;
        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Product name</th>");
        wr.println("</tr>");

        for (Product product : productRepository.findAll()) {
            wr.println("<tr>");
            wr.println("<td><a href='" + "info/?param1=" + product.getId() + "'>" + product.getId() + "</a></td>");
            wr.println("<td>" + product.getTitle() + "</td>");
            wr.println("</tr>");
        }
        wr.println("</table>");
    }

}
