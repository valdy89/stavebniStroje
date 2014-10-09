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
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author milos
 */
public class CustomerDaoImpl implements CustomerDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot to be null.");
        }
    
    }

    @Override
    public Customer getCustomer(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of the customer cannot be null.");
        }
        Customer customer = entityManager.find(Customer.class, id);
        return customer;
    
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer to be updated cannot to be null.");
        }
        entityManager.merge(customer);
    
    }

    @Override
    public void removeCustomer (Customer customer) {
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
