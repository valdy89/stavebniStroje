/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.DaoImpl;

import Entity.Customer;
import Entity.Machine;
import Entity.Rent;
import Util.LegalStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author milos
 */
public class RentDaoImplTest {
    @PersistenceUnit
    public EntityManagerFactory emf;
    
    @PersistenceUnit
    public EntityManager em;

    private RentDaoImpl instance;
    private Machine machine1;
    private Customer customer1;
    private Customer customer2;
    private Rent rent1;

    
    public RentDaoImplTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stroje");
        em = emf.createEntityManager();
        instance = new RentDaoImpl(em);

    }

    @BeforeClass
    public static void setUpClass() {
        

    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        em.getTransaction().begin();
        Machine machine1 = new Machine();
       
        machine1.setType("truck");
        machine1.setName("Volvo");
        machine1.setDescription("big truck");
       
        
        em.persist(machine1);
        em.refresh(machine1);
        this.machine1 = machine1;

        
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setFirstName("Jan");
        customer1.setSecondName("Zhanal");
        customer1.setAddress("tehnicka");
        customer1.setLegalStatus(LegalStatus.NATURAL);

        customer2.setFirstName("Ion");
        customer2.setSecondName("Fustatsu");
        customer2.setAddress("Ceska");
        customer2.setLegalStatus(LegalStatus.LEGAL); 
        
        em.persist(customer1);
        em.refresh(customer1);
        this.customer1 = customer1;
        em.persist(customer2);
        em.refresh(customer2);
        this.customer1 = customer2;
        
    
        
        Rent rent1 = new Rent();

        rent1.setStartOfRent(new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-01"));
        rent1.setEndOfRent(new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01"));
        rent1.setMachine(machine1);
        rent1.setCustomer(customer1);
     
        em.persist(rent1);
        em.refresh(rent1);
        this.rent1 = rent1;
      
        em.getTransaction().commit();         
    }
    
    @After
    public void tearDown() {
         em.close();
    }    


    @Test
    public void testPersist() throws ParseException {
        em.getTransaction().begin();        
        Rent rent = new Rent();
        rent.setStartOfRent(new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-21"));
        rent.setEndOfRent(new SimpleDateFormat("yyyy-MM-dd").parse("2014-08-21"));
        rent.setMachine(machine1);
        rent.setCustomer(customer1);
        
        instance.persist(rent);
        em.getTransaction().commit();
        assertTrue(rent.getId() > 0);
    }    

     @Test
     public void testUpdate() {
     em.getTransaction().begin();
     Customer rentBefore = em.find(Rent.class, rent1.getId()).getCustomer();
             rent1.setCustomer(customer2);
     instance.update(rent1);
     Customer rentNew = em.find(Rent.class, rent1.getId()).getCustomer();
     em.getTransaction().commit();
     
         assertNotSame(rentBefore,rentNew);
     }    
     
     @Test
     public void testRemove() {
     em.getTransaction().begin();
     Rent rentBefore = em.find(Rent.class, rent1.getId());
         assertNotNull(rentBefore);
     instance.remove(rent1);
     Rent rentNew = em.find(Rent.class, rent1.getId());
     assertNull(rentNew);
     em.getTransaction().commit();
     }     
}
