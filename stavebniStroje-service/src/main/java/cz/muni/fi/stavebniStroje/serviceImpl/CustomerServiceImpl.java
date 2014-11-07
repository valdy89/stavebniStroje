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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author test
 */
public class CustomerServiceImpl implements CustomerService {

    Mapper mapper = new DozerBeanMapper();
    private EntityManager entityManager;
    CustomerDao customerDao;

    @Transactional    
    @Override
    public void createCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Argument customerDto was null");
        }
        
        Customer customer = mapper.map(customerDto, Customer.class);

        entityManager.getTransaction();
        customerDao.persist(customer);
        entityManager.close();
    }

    @Required
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional 
    @Override
    public List<CustomerDto> findAllCustomer() {
        List<CustomerDto> customers = new ArrayList<>();
        for (Customer customer : customerDao.findAll()) {
            customers.add(mapper.map(customer, CustomerDto.class));
        }
        return customers;
    }

    @Transactional
    @Override
    public void updateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Argument customerDto was null");
        }
        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.update(customer);

    }

    @Transactional
    @Override
    public void removeCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("Argument customerDto was null");
        }
        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.remove(customer);

    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDto getCustomer(Long id) {
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        Customer customer;
        customer = customerDao.findById(id);
        return mapper.map(customer, CustomerDto.class);

    }

    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

}
