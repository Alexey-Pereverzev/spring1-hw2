package org.example.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CustomerDao {

    private final EntityManagerEngine engine;

    public CustomerDao(EntityManagerFactory emf) {
        engine = new EntityManagerEngine(emf);
    }

    public Customer findById(Long id) {
        return engine.executeInEM(em -> em.find(Customer.class, id));   // поиск по первичному ключу
    }

    public List<Customer> findAll() {
        return engine.executeInEM(em -> em.createQuery("from Customer", Customer.class).getResultList());
        //  запрос на список всех пользователей
    }

    public void deleteById(Long id) {
        engine.executeInTransaction(em -> {
            Customer customer = em.find(Customer.class, id);
            if (customer!=null) em.remove(customer);
        });
    }

    public void insert(Customer customer) {
        engine.executeInTransaction(em -> {
            List searched = em.createQuery("select id from Customer c where c.fullName = :fullName")   //  поиск покупателя по имени
                    .setParameter("fullName", customer.getFullName()).getResultList();
            if (searched.size()==0) {           //  SAVE
                em.persist(customer);
            }
        });
    }

}
