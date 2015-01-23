/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.dao;

import cz.muni.fi.stavebnistroje.entity.Customer;
import java.util.List;

/**
 * This interface is used for adding, updating,removing and searching customers
 * in the DB
 *
 * @author milos
 */
public interface CustomerDao {

    /**
     * Method for adding customer to the DB
     *
     * @param customer customer which should be added to the DB
     */
    public void persist(Customer customer);

    /**
     * This method returns customer which is owner of the Id
     *
     * @param id id of the customer which we want to find
     * @return customer which is owner of the given id
     */
    public Customer findById(Long id);
    
    public List<Customer> findByName(String name);

    /**
     * method is used for updating customer in the DB
     *
     * @param customer which needs to be updated
     */
    public void update(Customer customer);

    /**
     * This method is used to remove customer from the DB
     *
     * @param customer which should be removed from the DB
     */
    public void remove(Customer customer);

    /**
     * This method returns all customers from the DB
     *
     * @return list of customers from the DB
     */
    public List<Customer> findAll();
    
    
        public Customer findByUsername(String username);

}
