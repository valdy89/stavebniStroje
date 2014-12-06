/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.RentDao;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.service.RentService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dominik David
 */
@Service
@Transactional
public class RentServiceImpl implements RentService {

    private RentDao rentDao;
    
    @Autowired
    DozerBeanMapper dozerBeanMapper;

   
    @Required
    public void setRentDao(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    @Override
    public void newRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        try {
            Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
            rentDao.persist(rent);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot persist item due to exception", ex) {
            };
        }
    }

    @Override
    public void updateRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        try {
            Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
            rentDao.update(rent);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot update item due to exception", ex) {
            };
        }
    }

    @Override
    public void removeRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        try {
            Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
            rentDao.remove(rent);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot remove item due to exception", ex) {
            };
        }
    }

    @Override
    public RentDto findRentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        Rent rent;
        try {
            rent = rentDao.findById(id);
            return dozerBeanMapper.map(rent, RentDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read item due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RentDto> findAllRent() {
        Collection<RentDto> rents = new ArrayList<>();
        try {
            for (Rent rent : rentDao.findAll()) {
                rents.add(dozerBeanMapper.map(rent, RentDto.class));
            }
            return rents;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RentDto> findRentByCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Argument customerDto was null.");
        }
        Collection<RentDto> rents = new ArrayList<>();
        try {
            for (Rent rent : rentDao.findByCustomer(dozerBeanMapper.map(customerDto, Customer.class))) {
                rents.add(dozerBeanMapper.map(rent, RentDto.class));
            }
            return rents;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RentDto> findRentByMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null.");
        }
        Collection<RentDto> rents = new ArrayList<>();
        try {
            for (Rent rent : rentDao.findByMachine(dozerBeanMapper.map(machineDto, Machine.class))) {
                rents.add(dozerBeanMapper.map(rent, RentDto.class));
            }
            return rents;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RentDto> findRentByDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument date was null.");
        }
        Collection<RentDto> rents = new ArrayList<>();
        try{
        for (Rent rent : rentDao.findByDate(date)) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
        }catch(Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

}
