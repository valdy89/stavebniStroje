
package cz.muni.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik David
 * 
 * interface for Rents functions
 */
public interface RentService {

    void newRent(Rent rent);

    void updateRent(Rent rent);

    void removeRent(Rent rent);

    Rent findRentById(Long id);

    Collection<Rent> findAllRent();

    Collection<Rent> findRentByCustomer(Customer customer);

    Collection<Rent> findRentByMachine(Machine machine);

    Collection<Rent> findRentByStartDate(Date date);
    
    Collection<Rent> findRentByEndDate(Date date);

}
