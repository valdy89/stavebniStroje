/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Dao;

import Entity.Customer;
import Entity.Machine;
import Entity.Rent;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jiri Weiser, 374154
 */
public interface RentDao {

    // CRUD
    void persist(Rent rent);

    void update(Rent rent);

    void remove(Rent rent);

    Rent findById(Long id);
    
    // Other find methods
    List<Rent> findAll();
    
    List<Rent> findByCustomer(Customer customer);

    List<Rent> findByMachine(Machine machine);
    
    List<Rent> findByDuration(Duration duration);
    
    List<Rent> findByDate(Date date);
}
