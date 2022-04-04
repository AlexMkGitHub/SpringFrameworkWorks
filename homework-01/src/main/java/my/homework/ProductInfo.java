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
        String productTitle = req.getParameter("param2");
        double productCost = Double.parseDouble(req.getParameter("param3"));
        String productRuTitle = req.getParameter("param4");

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
        wr.println("<td>" + productId + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + productTitle + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + productCost + "</td>");
        wr.println("<th></th>");
        wr.println("<td>" + productRuTitle + "</td>");
        wr.println("</tr>");

        wr.println("</table>");
    }
}
