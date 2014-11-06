
package cz.muni.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik David
 * 
 * interface for Rents functions
 */
public interface RentService {

    void newRent(RentDto rentDto);

    void updateRent(RentDto rentDto);

    void removeRent(RentDto rentDto);

    RentDto findRentById(Long id);

    Collection<RentDto> findAllRent();

    Collection<RentDto> findRentByCustomer(CustomerDto customerDto);

    Collection<RentDto> findRentByMachine(MachineDto machineDto);
    
    Collection<RentDto> findRentByDate(Date date);


}
