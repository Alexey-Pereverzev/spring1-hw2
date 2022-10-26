package org.example;

import org.example.persist.Customer;
import org.example.persist.CustomerDao;
import org.example.persist.Product;
import org.example.persist.ProductDao;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        ProductDao dao = new ProductDao(emFactory);
        CustomerDao cDao = new CustomerDao(emFactory);


//        Product product = dao.findById(2L);       // поиск по ID
//        System.out.println(product);
//
//        List<Product> products = dao.findAll();   // получить все продукты
//        System.out.println(products);

//        dao.deleteById(4L);                        //  удалить продукт
//        System.out.println(dao.findAll());

//        Product product = dao.saveOrUpdate              //  обновить продукт (обновляем стоимость, если такой продукт есть)
//                (new Product("Влажный корм для собак и кошек органик картофель", "6500.00"));
//        product = dao.saveOrUpdate(new Product("Дралка для котов", "2500.00"));
//                                                        //  добавляем продукт, если такого нет
//        System.out.println(dao.findAll());


//  =====================================================================================================


//        Customer customer = cDao.findById(2L);       // поиск по ID
//        System.out.println(customer);

//        List<Customer> customers = cDao.findAll();   // получить всех покупателей
//        System.out.println(customers);
//
//        cDao.deleteById(1L);                        //  удалить покупателя
//        System.out.println(cDao.findAll());
//
//        cDao.insert(new Customer("Ангелина Легкова"));   //  добавить покупателя
//        System.out.println(cDao.findAll());


        emFactory.close();
    }
}