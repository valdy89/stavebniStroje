/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.RentDao;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.service.RentService;
import cz.muni.fi.stavebniStroje.serviceImpl.RentServiceImpl;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author test
 */

public class RentServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private RentService rentService =  new RentServiceImpl();

    @Mock
    private RentDao rentDao;

    @Autowired
    private DozerBeanMapper mapper;

    public RentServiceTest() {
          MockitoAnnotations.initMocks(this);
    }

    @Before
    public void before() {
        
        ReflectionTestUtils.setField(rentService, "dozerBeanMapper", mapper);
        ReflectionTestUtils.setField(rentService, "rentDao", rentDao);
    }

    @After
    public void after() {
        mapper = null;
    }

    @Test
    public void testNewRent() {
        try {
            rentService.newRent(null);
            fail("Didn't throw exception when rent to be created is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Rent to be created is null.");
        }

        Rent rent = new Rent();
       
        RentDto rentDto = mapper.map(rent, RentDto.class);
        rentService.newRent(rentDto);
        verify(rentDao).persist(rent);
    }

    @Test
    public void testUpdateRent() {
        try {
            rentService.updateRent(null);
            fail("Didn't throw exception when rent to be updated is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Rent to be updated is null.");
        }

        Rent rent = new Rent();

        RentDto rentDto = mapper.map(rent, RentDto.class);
        rentService.updateRent(rentDto);
        verify(rentDao).update(rent);
    }

    @Test
    public void testRemoveRent() {
        try {
            rentService.removeRent(null);
            fail("Didn't throw exception when rent to be removed is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Rent to be removed is null.");
        }

        Rent rent = new Rent();

        RentDto rentDto = mapper.map(rent, RentDto.class);
        rentService.removeRent(rentDto);
        verify(rentDao).remove(rent);
    }

    @Test
    public void testFindRent() {
        try {
            rentService.findRentById(null);
            fail("Didn't throw exception when id is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Id is null.");
        }

        when(rentDao.findById(new Long(1))).thenReturn(new Rent());
        rentService.findRentById(new Long(1));
        verify(rentDao).findById(new Long(1));
    }

    @Test
    public void testFindByCustomer() {

        Collection<Rent> col = new ArrayList<>();
        col.add(new Rent());
        col.add(new Rent());

        Collection<RentDto> expected = new ArrayList<>();
        for (Rent r : col) {
            expected.add(mapper.map(r, RentDto.class));
        }

        try {
            rentService.findRentByCustomer(null);
            fail("Didn't throw exception when customer is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Date is null.");
        }
        Customer customer = new Customer();
        CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        
        when(rentDao.findByCustomer(customer)).thenReturn(col);
        assertEquals(expected, rentService.findRentByCustomer(customerDto));
    }

    @Test
    public void testFindByMachine() {

        Collection<Rent> col = new ArrayList<>();
        col.add(new Rent());
        col.add(new Rent());

        Collection<RentDto> expected = new ArrayList<>();
        for (Rent r : col) {
            expected.add(mapper.map(r, RentDto.class));
        }

        try {
            rentService.findRentByCustomer(null);
            fail("Didn't throw exception when customer is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Date is null.");
        }
        Machine machine = new Machine();
        MachineDto machineDto = mapper.map(machine, MachineDto.class);
        
        when(rentDao.findByMachine(machine)).thenReturn(col);
        assertEquals(expected, rentService.findRentByMachine(machineDto));
    }
    @Test
    public void testFindByDate() {

        Collection<Rent> col = new ArrayList<>();
        col.add(new Rent());
        col.add(new Rent());

        Collection<RentDto> expected = new ArrayList<>();
        for (Rent r : col) {
            expected.add(mapper.map(r, RentDto.class));
        }

        try {
            rentService.findRentByCustomer(null);
            fail("Didn't throw exception when customer is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Date is null.");
        }
        Date now = new Date();
        when(rentDao.findByDate(now)).thenReturn(col);
        assertEquals(expected, rentService.findRentByDate(now));
    }

    @Test
    public void testFindAll() {

        Collection<Rent> col = new ArrayList<>();
        col.add(new Rent());
        col.add(new Rent());

        Collection<RentDto> expected = new ArrayList<>();
        for (Rent r : col) {
            expected.add(mapper.map(r, RentDto.class));
        }

        when(rentDao.findAll()).thenReturn(col);
        assertEquals(expected, rentService.findAllRent());
    }
}
