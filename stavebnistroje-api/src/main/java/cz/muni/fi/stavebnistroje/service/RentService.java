
package cz.muni.fi.stavebnistroje.service;


import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.util.DateRangeException;
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

    void newRent(RentDto rentDto) throws DataAccessException, DateRangeException;

    void updateRent(RentDto rentDto) throws DataAccessException, DateRangeException;

    void removeRent(RentDto rentDto) throws DataAccessException;

    RentDto findRentById(Long id) throws DataAccessException;

    Collection<RentDto> findAllRent() throws DataAccessException;

    Collection<RentDto> findRentByDate(Date date) throws DataAccessException;

}
