/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.test.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.MachineDao;
import cz.muni.fi.stavebnistroje.dao.RentDao;
import cz.muni.fi.stavebnistroje.dto.CustomerDto;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.entity.Customer;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Rent;
import cz.muni.fi.stavebnistroje.service.RentService;
import cz.muni.fi.stavebnistroje.serviceImpl.RentServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
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
    
    @Mock
    private MachineDao machineDao;

    @Autowired
    private DozerBeanMapper mapper;

    public RentServiceTest() {
          MockitoAnnotations.initMocks(this);
    }

    @Before
    public void before() {
        
        ReflectionTestUtils.setField(rentService, "dozerBeanMapper", mapper);
        ReflectionTestUtils.setField(rentService, "rentDao", rentDao);
        ReflectionTestUtils.setField(rentService, "machineDao", machineDao);
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
        rent.setStartOfRent(new Date());
        rent.setEndOfRent(new Date());
        rent.setMachine(new Machine());
        rent.getMachine().setId(1);
        when(machineDao.findById(new Long(1))).thenReturn(new Machine());

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
        rent.setStartOfRent(new Date());
        rent.setEndOfRent(new Date());
        rent.setMachine(new Machine());
        rent.getMachine().setId(1);
        when(machineDao.findById(new Long(1))).thenReturn(new Machine());

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
    public void testFindByDate() {

        Collection<Rent> col = new ArrayList<>();
        col.add(new Rent());
        col.add(new Rent());

        Collection<RentDto> expected = new ArrayList<>();
        for (Rent r : col) {
            expected.add(mapper.map(r, RentDto.class));
        }

        try {
            rentService.findRentByDate(null);
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
