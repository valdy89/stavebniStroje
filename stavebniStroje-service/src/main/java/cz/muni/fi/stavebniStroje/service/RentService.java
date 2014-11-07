
package cz.muni.fi.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import java.util.Collection;
import java.util.Date;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Dominik David
 * 
 * interface for Rents functions
 */
public interface RentService {

    void newRent(RentDto rentDto) throws DataAccessException;

    void updateRent(RentDto rentDto) throws DataAccessException;

    void removeRent(RentDto rentDto) throws DataAccessException;

    RentDto findRentById(Long id) throws DataAccessException;

    Collection<RentDto> findAllRent() throws DataAccessException;

    Collection<RentDto> findRentByCustomer(CustomerDto customerDto) throws DataAccessException;

    Collection<RentDto> findRentByMachine(MachineDto machineDto) throws DataAccessException;
    
    Collection<RentDto> findRentByDate(Date date) throws DataAccessException;

}
