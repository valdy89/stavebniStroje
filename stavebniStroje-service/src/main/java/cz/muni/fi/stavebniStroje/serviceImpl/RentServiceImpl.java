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
import cz.muni.stavebniStroje.service.RentService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dominik David
 */
public class RentServiceImpl implements RentService {
    
    private RentDao rentDao;
    private EntityManager entityManager;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public void newRent(RentDto rentDto) {
         if (rentDto == null) {
            throw new NullPointerException("Argument rentDto is null");
        }
        
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        entityManager.getTransaction();
        rentDao.persist(rent);
        entityManager.close();
    }

    @Override
    public void updateRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new NullPointerException("Argument rentDto is null");
        }
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        rentDao.update(rent);
    }

    @Override
    public void removeRent(RentDto rentDto) {
         if (rentDto == null) {
            throw new NullPointerException("Argument rentDto is null");
        }
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        rentDao.remove(rent);
    }

    @Override
    public RentDto findRentById(Long id) {
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        Rent rent;
        rent = rentDao.findById(id);
        return dozerBeanMapper.map(rent, RentDto.class);
    }

    @Override
    public Collection<RentDto> findAllRent() {
        List<RentDto> rents = new ArrayList<>();
        for (Rent rent : rentDao.findAll()) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

    @Override
    public Collection<RentDto> findRentByCustomer(CustomerDto customerDto) {
        List<RentDto> rents = new ArrayList<>();  
        for (Rent rent : rentDao.findByCustomer(dozerBeanMapper.map(customerDto, Customer.class))) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

    @Override
    public Collection<RentDto> findRentByMachine(MachineDto machineDto) {
        List<RentDto> rents = new ArrayList<>();  
        for (Rent rent : rentDao.findByMachine(dozerBeanMapper.map(machineDto, Machine.class))) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

    @Override
    public Collection<RentDto> findRentByDate(Date date) {
        List<RentDto> rents = new ArrayList<>();
        for (Rent rent : rentDao.findByDate(date)) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

    
}
