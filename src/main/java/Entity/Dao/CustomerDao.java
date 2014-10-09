/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Dao;

import Entity.Customer;
import java.util.List;
import Util.LegalStatus;

/**
 *
 * @author milos
 */
public interface CustomerDao {
    
    public void create(Customer customer);
    public Customer get(Long id);
    public void update(Customer customer);
    public void remove(Customer customer);
    public List<Customer> findAll();
    public Customer findByLegalStatus(LegalStatus legalStatus);
    

    
}