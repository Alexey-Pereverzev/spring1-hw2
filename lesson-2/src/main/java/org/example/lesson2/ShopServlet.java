package org.example.lesson2;

import org.example.lesson2.matrix.Product;
import org.example.lesson2.matrix.ProductMatrix;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/shop-servlet")                        //  http://localhost:8080/servlet-app/shop-servlet
// сервлет магазина
public class ShopServlet extends HttpServlet {
    private ProductMatrix matrix;

    @Override
    public void init() {
        matrix = (ProductMatrix) getServletContext().getAttribute("productMatrix");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("<html><body><h1>Выводим список продуктов</h1></body></html>");
        ArrayList<Product> products = matrix.findAll();
        for (Product product : products) {
            resp.getWriter().println("<p>Наименование: " + product.getTitle() + "</p>");
            resp.getWriter().println("<p>Цена: " + String.format("%.2f", product.getCost()) + "</p>");
            resp.getWriter().println("<p></p>");
        }

    }
}
