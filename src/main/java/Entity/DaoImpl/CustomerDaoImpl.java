/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Customer;
import Entity.Dao.CustomerDao;
import java.util.List;
import Util.LegalStatus;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author milos
 */
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    @Override
    public void persist(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot to be null.");
        }
        entityManager.persist(customer);
        entityManager.flush();
        entityManager.refresh(customer);
       
    }

    @Override
    public Customer findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of the customer cannot be null.");
        }
        Customer customer = entityManager.find(Customer.class, id);
        return customer;

    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer to be updated cannot to be null.");
        }
        entityManager.merge(customer);

    }

    @Override
    public void remove(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer to be removed cannot to be null.");
        }
        Customer c = entityManager.find(Customer.class, customer.getId());
        if (c == null) {
            throw new IllegalArgumentException("Customer doesn't exist in the database.");
        }
        entityManager.remove(c);

    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> tq = entityManager.createQuery(
                "SELECT customer FROM Customer as customer ORDER BY customer.secondName, customer.firstName", Customer.class);
        return tq.getResultList();
    }

}
