/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.RentDao;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.entity.Revision;
import cz.muni.fi.stavebniStroje.service.RentService;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author test
 */
@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    private Rent r1;
    private Rent r2;
    private Rent r3;

    private RentService rentrService;

    @Mock
    private RentDao rentDao;

    @Mock
    private EntityManager entityManager;
    private DozerBeanMapper mapper;

    private Rent setRent(Machine machine, Customer customer, Date startOfRent, Date endOfRent) {
        Rent rent = new Rent();
        rent.setCustomer(customer);
        rent.setMachine(machine);
        rent.setStartOfRent(startOfRent);
        rent.setEndOfRent(endOfRent);

        return rent;
    }

    private Customer setNewCustomer(String firstName, String lastname, String address, LegalStatus legalStatus) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSecondName(lastname);
        customer.setAddress(address);
        customer.setLegalStatus(legalStatus);

        return customer;
    }
    
    private Machine setNewMachine(String name, String type, String description, Collection<Rent> rents,Collection<Revision> revisions, BigDecimal price) {
        Machine machine = new Machine();
        machine.setName(name);
        machine.setType(type);
        machine.setDescription(description);
        machine.setRents(rents);
        machine.setRevisions(revisions);
        machine.setPrice(price);
               
        return machine;
        
    }


        @Before
    public void setUp() throws ParseException {
        mapper = new DozerBeanMapper();
    }
    
}
