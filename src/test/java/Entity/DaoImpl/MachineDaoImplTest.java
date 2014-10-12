/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Machine;
import Entity.Rent;
import Entity.Revision;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.joda.money.BigMoney;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milan
 */
public class MachineDaoImplTest {

    @PersistenceUnit
    public EntityManagerFactory emf;
    
    @PersistenceUnit
    public EntityManager em;

    MachineDaoImpl instance;
    Machine machine;

    public MachineDaoImplTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stroje");
        em = emf.createEntityManager();
        instance = new MachineDaoImpl(em);

    }

    @BeforeClass
    public static void setUpClass() {
        

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
       em.getTransaction().begin();
        Machine machine = new Machine();
        machine.setType("P");
        machine.setName("Persist");
        machine.setDescription("Persis");
        Collection<Rent> rent = new ArrayList<Rent>();
        Collection<Revision> revisions = new ArrayList<Revision>();
        machine.setRents(rent);
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
         machine = new Machine();
        machine.setType("4568");
        machine.setName("Test");
        machine.setDescription("test");
        Set<Rent> rent = new HashSet<Rent>();
        machine.setRents(rent);
        
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
     
         assertNotSame(machineNew,machineBefore);
     }

     @Test
     public void testRemove() {
     em.getTransaction().begin();
     Machine machineBefore = em.find(Machine.class, machine.getId());
         assertNotNull(machineBefore);
     instance.remove(machine);
     Machine machineNew = em.find(Machine.class, machine.getId());
     assertNull(machineNew);
     em.getTransaction().commit();
     }

    /**
     * Test of findById method, of class MachineDaoImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        MachineDaoImpl instance = null;
        Machine expResult = null;
        Machine result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class MachineDaoImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        MachineDaoImpl instance = null;
        List<Machine> expResult = null;
        List<Machine> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByType method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByType() {
        System.out.println("findByType");
        String type = "";
        MachineDaoImpl instance = null;
        List<Machine> expResult = null;
        List<Machine> result = instance.findByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByRevisionDate method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByRevisionDate_Date() {
        System.out.println("findByRevisionDate");
        Date specificDate = null;
        MachineDaoImpl instance = null;
        List<Machine> expResult = null;
        List<Machine> result = instance.findByRevisionDate(specificDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByRevisionDate method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByRevisionDate_Date_Date() {
        System.out.println("findByRevisionDate");
        Date dateFrom = null;
        Date dateTo = null;
        MachineDaoImpl instance = null;
        List<Machine> expResult = null;
        List<Machine> result = instance.findByRevisionDate(dateFrom, dateTo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrice method, of class MachineDaoImpl.
     */
    @Test
    public void testFindByPrice() {
        System.out.println("findByPrice");
        BigMoney price = null;
        MachineDaoImpl instance = null;
        List<Machine> expResult = null;
        List<Machine> result = instance.findByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
