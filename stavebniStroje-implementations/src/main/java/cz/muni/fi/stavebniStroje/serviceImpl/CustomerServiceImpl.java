/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.CustomerDao;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is used for work with customerDao objects
 *
 * @author Milos
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void createCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Argument customerDto was null");
        }

        Customer customer = dozerBeanMapper.map(customerDto, Customer.class);

        customerDao.persist(customer);

    }

    @Transactional
    @Override
    public Collection<CustomerDto> findAllCustomer() {
        Collection<CustomerDto> customers = new ArrayList<>();
        try {
            for (Customer customer : customerDao.findAll()) {
                customers.add(dozerBeanMapper.map(customer, CustomerDto.class));
            }
            return customers;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Transactional
    @Override
    public void updateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Argument customerDto was null");
        }
        try {
            Customer customer = dozerBeanMapper.map(customerDto, Customer.class);
            customerDao.update(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot update item due to exception", ex) {
            };
        }
    }

    @Transactional
    @Override
    public void removeCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Argument customerDto was null");
        }
        try {
            Customer customer = dozerBeanMapper.map(customerDto, Customer.class);
            customerDao.remove(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot remove item due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDto getCustomer(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        try {
            Customer customer;
            customer = customerDao.findById(id);
            return dozerBeanMapper.map(customer, CustomerDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read item due to exception", ex) {
            };
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws DataAccessException
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<CustomerDto> searchCustomer(String name) throws DataAccessException {
        if (name == null) {
            throw new IllegalArgumentException("Argument string name was null");
        }

        Collection<CustomerDto> customers = new ArrayList<>();
        try {

            for (Customer customer : customerDao.findByName(name)) {
                customers.add(dozerBeanMapper.map(customer, CustomerDto.class));
            }
            return customers;

        } catch (Exception ex) {
            throw new DataAccessException("Cannot read item due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDto findByUsername(String username) {
        Customer customer = customerDao.findByUsername(username);
        if (customer == null) {
            return null;
        }
        return dozerBeanMapper.map(customer, CustomerDto.class);
    }
}
