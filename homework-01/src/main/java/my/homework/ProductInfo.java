package my.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/info/*")
public class ProductInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Integer.parseInt(req.getParameter("param1"));

        Product product = ProductServlet.globalProdRep.findById(productId);


        PrintWriter wr = resp.getWriter();
        wr.println("<table>");

        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th></th>");
        wr.println("<th>Product</th>");
        wr.println("<th></th>");
        wr.println("<th>Cost</th>");
        wr.println("<th></th>");
        wr.println("<th>RuTittle</th>");
        wr.println("</tr>");

        wr.println("<tr>");
        wr.println("<td>" + product.getId() + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + product.getTitle() + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + product.getCost() + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + product.getRuTitle() + "</td>");
        wr.println("</tr>");

        wr.println("</table>");
    }
}
