package org.example;

import org.example.persist.Product;
import org.example.persist.ProductDao;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao dao = new ProductDao(emFactory);

//        dao.initDB();                             // инициализация БД

//        Product product = dao.findById(2L);       // поиск по ID
//        System.out.println(product);

//        List<Product> products = dao.findAll();   // получить все продукты
//        System.out.println(products);

//        dao.deleteById(10L);                        //  удалить продукт
//        System.out.println(dao.findAll());

//        Product product = dao.saveOrUpdate              //  обновить продукт (обновляем стоимость, если такой продукт есть)
//                (new Product("Влажный корм для собак и кошек органик картофель", "5500.00"));
//        product = dao.saveOrUpdate(new Product("Дралка для котов", "2500.00"));
//                                                        //  добавляем продукт, если такого нет
//        System.out.println(dao.findAll());



        emFactory.close();
    }
}