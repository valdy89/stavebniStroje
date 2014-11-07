/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.CustomerDao;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import cz.muni.fi.stavebniStroje.serviceImpl.CustomerServiceImpl;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.text.ParseException;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Milos Petrovic
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private Customer c1;
    private Customer c2;
    private Customer c3;

    
    private CustomerService customerService;
    
    @Mock
    private CustomerDao customerDao;
    @Mock
    private EntityManager entityManager;
    private DozerBeanMapper mapper;

    private Customer setNewCustomer(String firstName, String lastname, String address, LegalStatus legalStatus) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSecondName(lastname);
        customer.setAddress(address);
        customer.setLegalStatus(legalStatus);

        return customer;
    }

    @Before
    public void setUp() throws ParseException {
        mapper = new DozerBeanMapper();
        customerService = new CustomerServiceImpl();
        ReflectionTestUtils.setField(customerService, "customerDao", customerDao);
        ReflectionTestUtils.setField(customerService, "dozerBeanMapper", mapper);
        ReflectionTestUtils.setField(customerService, "entityManager", entityManager);
        c1 = this.setNewCustomer("Petar", "Petrovic", "Tehnicka 9", LegalStatus.LEGAL);
        c2 = this.setNewCustomer("Jan", "Novak", "Ceska 9", LegalStatus.LEGAL);
        c3 = this.setNewCustomer("Nick", "Foster", "Uvoz 9", LegalStatus.LEGAL);
    }

    @After
    public void tearDown() {
        customerDao = null;
        customerService = null;
    }

    /**
     * Test of createCustomer method from CustomerServiceImpl.
     */
    @Test
    public void testCreateCustomer() {
        try {
            customerService.createCustomer(null);
            fail("Didn't throw exception when customer to be created is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Thrown unexpected exception though customer to be created is null.");
        }

        Customer customer = this.setNewCustomer("Petr", "Novak", "Vinarska 5", LegalStatus.LEGAL);
        CustomerDto custDto = mapper.map(customer, CustomerDto.class);
        customerService.createCustomer(custDto);
        verify(customerDao).persist(customer);

    }
}
