package org.example.lesson2;

import org.example.lesson2.matrix.Product;
import org.example.lesson2.matrix.ProductMatrix;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ProductMatrix matrix = new ProductMatrix();
        sc.setAttribute("productMatrix", matrix);
        matrix.insert(new Product(0, "Набор пробников для кошек", 1249.90));
        matrix.insert(new Product(0, "Печенье для собак вкус яблока", 549.90));
        matrix.insert(new Product(0, "Гуляш для собак биг пак", 6099.90));
        matrix.insert(new Product(0, "Пробник для кошек New", 315.50));
        matrix.insert(new Product(0, "Набор кормов для собак", 1769.90));
        matrix.insert(new Product(0, "Жвачка для собак и кошек клюква", 1179.90));
        matrix.insert(new Product(0, "Чудо фермент для кошек и собак", 2499.90));
        matrix.insert(new Product(0, "Влажный корм для собак и кошек органик картофель", 4899.90));
        matrix.insert(new Product(0, "Жевательные добавки для щенков", 2599.90));
        matrix.insert(new Product(0, "Жевательные добавки для взрослых собак", 1622.50));
    }
}
