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
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author test
 */
public class CustomerServiceImpl implements CustomerService {

    Mapper mapper = new DozerBeanMapper();
    private EntityManager entityManager;
    CustomerDao customerDao;

    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Required
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void createCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Argument customerDto was null");
        }

        Customer customer = mapper.map(customerDto, Customer.class);
        try {
            entityManager.getTransaction();
            customerDao.persist(customer);
            entityManager.close();
        } catch (Exception ex) {
            throw new DataAccessException("Cannot persist item due to exception.", ex) {
            };
        }
    }

    @Transactional
    @Override
    public Collection<CustomerDto> findAllCustomer() {
        Collection<CustomerDto> customers = new ArrayList<>();
        try {
            for (Customer customer : customerDao.findAll()) {
                customers.add(mapper.map(customer, CustomerDto.class));
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
            throw new NullPointerException("Argument customerDto was null");
        }
        try {
            Customer customer = mapper.map(customerDto, Customer.class);
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
            throw new NullPointerException("Argument customerDto was null");
        }
        try {
            Customer customer = mapper.map(customerDto, Customer.class);
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
            throw new NullPointerException("Argument id was null");
        }
        try {
            Customer customer;
            customer = customerDao.findById(id);
            return mapper.map(customer, CustomerDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read item due to exception", ex) {
            };
        }
    }

}
