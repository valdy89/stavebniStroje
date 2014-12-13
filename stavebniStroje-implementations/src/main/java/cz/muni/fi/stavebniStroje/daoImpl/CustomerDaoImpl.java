/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.daoImpl;

import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.dao.CustomerDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This class implements CustomerDao interface which is used for managing
 * customer in DB
 *
 * @author milos
 */
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDaoImpl() {
    }

    
    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Method for adding customer to the DB
     *
     * @param customer customer which should be added to the DB
     */
    @Override
    public void persist(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot to be null.");
        }
        entityManager.persist(customer);
        

    }

    /**
     * This method returns customer which is owner of the Id
     *
     * @param id id of the customer which we want to find
     * @return customer which is owner of the given id
     */
    @Override
    public Customer findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of the customer cannot be null.");
        }
        Customer customer = entityManager.find(Customer.class, id);
        return customer;

    }

    /**
     * method is used for updating customer in the DB
     *
     * @param customer which needs to be updated
     */
    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer to be updated cannot to be null.");
        }
        if (findById(customer.getId()) == null) {
            throw new IllegalArgumentException("Customer must exists before updating.");
        }
        entityManager.merge(customer);

    }

    /**
     * This method is used to remove customer from the DB
     *
     * @param customer which should be removed from the DB
     */
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

    /**
     * This method returns all customers from the DB
     *
     * @return list of customers from the DB
     */
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> tq = entityManager.createQuery(
                "SELECT customer FROM Customer as customer ORDER BY customer.secondName, customer.firstName", Customer.class);
        return tq.getResultList();
    }
    
    @Override
    public List<Customer> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Customer to be find by name cannot to be null");
        }
        TypedQuery<Customer> tq = entityManager.createQuery(
                "SELECT customer FROM Customer customer WHERE firstName LIKE '%"+name+"%' OR secondName LIKE '%"+name+"%'", Customer.class);

        return  tq.getResultList();
    }

    @Override
    //public List<Customer> findByUsername(String username) {
    public Customer findByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Customer's username cannot to be null");
        }
        TypedQuery<Customer> tq = entityManager.createQuery(
                "SELECT customer FROM Customer customer WHERE username='"+username+"'", Customer.class);

        //mislim ze veliky rozdil nebude i pokud nechame aby vystup byl list, protoze bude obsahovat jenom jeden element
        //return  tq.getResultList();
        return  tq.getSingleResult();
    }
    
}
