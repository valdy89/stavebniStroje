/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.daoImpl;

import cz.muni.fi.stavebniStroje.daoImpl.MachineDaoImpl;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.entity.Revision;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milan Valúšek
 */
public class MachineDaoImplTest {

    @PersistenceUnit
    public EntityManagerFactory emf;

    @PersistenceUnit
    public EntityManager em;

    MachineDaoImpl instance;
    Machine machine;

    public MachineDaoImplTest() {
        emf = Persistence.createEntityManagerFactory("stroje");

    }

    @Before
    public void setUp() {
        
        em = emf.createEntityManager();
        instance = new MachineDaoImpl(em);
        em.getTransaction().begin();
        Machine machine = new Machine();
        machine.setType("P");
        machine.setName("Persist");
        machine.setDescription("Persis");
        machine.setPrice(new BigDecimal(1000));
        Rent rent1 = new Rent();
        rent1.setMachine(machine);
        rent1.setStartOfRent(new Date(2010, 10, 10));
        rent1.setEndOfRent(new Date(2010, 10, 15));
        rent1.setCustomer(new Customer());
        Collection<Rent> rents = new ArrayList<Rent>();
        Collection<Revision> revisions = new ArrayList<Revision>();
        rents.add(rent1);
        machine.setRents(rents);
        machine.setRevisions(revisions);

        em.persist(machine);
        
        
        em.refresh(machine);
        this.machine = machine;
        em.getTransaction().commit();

    }

    @After
    public void tearDown() {
        em.close();
    }

    /**
     * Test of persist method, of class MachineDaoImpl.
     */
    @Test
    public void testPersist() {

        em.getTransaction().begin();
        instance.persist(machine);
        em.getTransaction().commit();
        assertTrue(machine.getId() > 0);
    }

    /**
     * Test of update method, of class MachineDaoImpl.
     */
    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        String machineBefore = em.find(Machine.class, machine.getId()).getName();
        machine.setName("New persist");
        instance.update(machine);
        String machineNew = em.find(Machine.class, machine.getId()).getName();
        em.getTransaction().commit();

        assertNotSame(machineNew, machineBefore);
    }

    @Test
    public void testRemove() {

        em.getTransaction().begin();
        Machine machineBefore = em.find(Machine.class, machine.getId());
        assertNotNull(machineBefore);
        instance.remove(machine);
        Machine machineNew = em.find(Machine.class, machine.getId());
        em.getTransaction().commit();

        assertNull(machineNew);

    }

    /**
     * Test of findById method, of class MachineDaoImpl.
     */
    @Test
    public void testFindById() {
        
        em.getTransaction().begin();
        Machine m = instance.findById(machine.getId());
        em.getTransaction().commit();
        assertEquals(machine, m);
        
       
    }

    /**
     * Test of findAll method, of class MachineDaoImpl.
     */
    @Test
    public void testFindAll() {
     
        Collection<Machine> expResult = new ArrayList();
        expResult.add(machine);
        em.getTransaction().begin();
        Collection<Machine> result = instance.findAll();
        em.getTransaction().commit();
        
         assertEquals(expResult, result);
        
    }

    /**
     * Test of findByType method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByType() {
        
         Collection<Machine> expResult = new ArrayList();
        expResult.add(machine);
        em.getTransaction().begin();
        Collection<Machine> result = instance.findByType(machine.getType());
        Collection<Machine> notResult = instance.findByType("P123");
        em.getTransaction().commit();
        
         assertEquals(expResult, result);
         assertNotSame(notResult, expResult);
    }
    
    

    

    /**
     * Test of findByPrice method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByPrice() {
       
      
         Collection<Machine> expResult = new ArrayList();
        expResult.add(machine);
        em.getTransaction().begin();
        Collection<Machine> result = instance.findByPrice(new BigDecimal(1000));
        Collection<Machine> notResult = instance.findByPrice(new BigDecimal(10020));
        em.getTransaction().commit();
        
         assertEquals(expResult, result);
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
         em.getTransaction().begin();
        instance.remove(null);
         em.getTransaction().commit();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdNull() {
         em.getTransaction().begin();
        instance.findById(null);
         em.getTransaction().commit();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNull() {
         em.getTransaction().begin();
        instance.update(null);
         em.getTransaction().commit();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPersistNull() {
         em.getTransaction().begin();
        instance.persist(null);
         em.getTransaction().commit();
    }
    /*
    @Test
    public void testFindByRentDate_Date_Date() {
       Collection<Machine> expResult = new ArrayList();
        expResult.add(machine);
        em.getTransaction().begin();
        
        List<Machine> result = instance.findByRentDate(new Date(2010, 10, 5), new Date(2010, 10, 20));
         em.getTransaction().commit();
        assertEquals(expResult, result);
        
    }*/
    
}

