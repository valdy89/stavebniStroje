/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Machine;
import Entity.Rent;
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
    
    MachineDaoImpl instance = new MachineDaoImpl();

    public MachineDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stroje");
        EntityManager em = emf.createEntityManager();
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of persist method, of class MachineDaoImpl.
     */
    @Test
    public void testPersist() {
        System.out.println("persist");
        Machine machine =  new Machine();
        machine.setType("4568");
        machine.setName("Test");
        machine.setDescription("test");
        Set<Rent> rent = new HashSet<Rent>();
        machine.setRents(rent);
       
        instance.persist(machine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
/*
    /**
     * Test of update method, of class MachineDaoImpl.
     
    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        Machine machine = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        instance.update(machine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class MachineDaoImpl.
     
    @Test
    public void testRemove() {
        System.out.println("remove");
        Machine machine = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        instance.remove(machine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class MachineDaoImpl.
     
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        Machine expResult = null;
        Machine result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    *
     * Test of findAll method, of class MachineDaoImpl.
     *//*
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        MachineDaoImpl instance = new MachineDaoImpl();
        List<Machine> expResult = null;
        List<Machine> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of findByType method, of class MachineDaoImpl.
     *//*
    @Test
    public void testFindByType() {
        System.out.println("findByType");
        String type = "";
        MachineDaoImpl instance = new MachineDaoImpl();
        List<Machine> expResult = null;
        List<Machine> result = instance.findByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of findByRevisionDate method, of class MachineDaoImpl.
     
    @Test
    public void testFindByRevisionDate_Date() {
        System.out.println("findByRevisionDate");
        Date specificDate = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        List<Machine> expResult = null;
       // List<Machine> result = instance.findByRevisionDate(specificDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */
    /**
     * Test of findByRevisionDate method, of class MachineDaoImpl.
     */
    /*
    @Test
    public void testFindByRevisionDate_Date_Date() {
        System.out.println("findByRevisionDate");
        Date dateFrom = null;
        Date dateTo = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        List<Machine> expResult = null;
        List<Machine> result = instance.findByRevisionDate(dateFrom, dateTo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of findByPrice method, of class MachineDaoImpl.
     */
  /*  @Test
    public void testFindByPrice() {
        System.out.println("findByPrice");
        BigMoney price = null;
        MachineDaoImpl instance = new MachineDaoImpl();
        List<Machine> expResult = null;
        List<Machine> result = instance.findByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
