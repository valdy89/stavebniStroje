/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Dao;

import Entity.Customer;
import java.util.List;


/**
 *
 * @author milos
 */
public interface CustomerDao {
    
    public void createCustomer(Customer customer);
    public Customer getCustomer(Long id);
    public void updateCustomer(Customer customer);
    public void removeCustomer(Customer customer);
    public List<Customer> findAll();
   

    
}