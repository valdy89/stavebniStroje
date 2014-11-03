/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.CustomerDao;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.stavebniStroje.service.CustomerService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author test
 */
public class CustomerServiceImpl implements CustomerService {

    Mapper mapper = new DozerBeanMapper();
    private EntityManager entityManager;
    CustomerDao customerDao;

   //task from C2: You can use Dozer framework to map entity instances to transfer objects. The mapping may be done on Service Layer
   // Basic: DestinationObject destObject = mapper.map(sourceObject, DestinationObject.class);
   //source: http://dozer.sourceforge.net/documentation/usage.html    
    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);

        entityManager.getTransaction();
        customerDao.persist(customer);
        entityManager.close();
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCustomer(CustomerDto customer) {
    }

    @Override
    public void removeCustomer(CustomerDto customer) {
    }

    @Override
    public List<CustomerDto> findAllCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

}
