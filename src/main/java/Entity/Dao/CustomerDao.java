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

    public void persist(Customer customer);

    public Customer findById(Long id);

    public void update(Customer customer);

    public void remove(Customer customer);

    public List<Customer> findAll();

}
