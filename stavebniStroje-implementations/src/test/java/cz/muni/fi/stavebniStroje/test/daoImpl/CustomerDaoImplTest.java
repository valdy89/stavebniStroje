/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.daoImpl;

import cz.muni.fi.stavebniStroje.daoImpl.CustomerDaoImpl;
import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jiri Weiser, 374154
 */
public class CustomerDaoImplTest {
 
    @PersistenceUnit
    public EntityManagerFactory emf;
    
    @PersistenceUnit
    public EntityManager em;

    CustomerDaoImpl instance;
    
    public CustomerDaoImplTest() {
        emf = Persistence.createEntityManagerFactory("stroje-test");
    }
    
    @Before
    public void before() {
        em = emf.createEntityManager();
        instance = new CustomerDaoImpl(em);
    }

    @After
    public void after() {
        em.close();
    }
    
    @Test
    public void testPersist() {
        Customer customer = create();
        em.getTransaction().begin();
        instance.persist(customer);
        em.getTransaction().commit();
        Assert.assertTrue(customer.getId() > 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPersistNull() {
        instance.persist(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNull() {
        instance.update(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        instance.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonexistent() {
        instance.update(create());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexitent() {
        instance.remove(create());
    }
    
    @Test
    public void testUpdate() {
        Customer customer = create();
        
        insert(customer);
        String newName = "tralala";
        // update
        customer.setFirstName(newName);
        
        em.getTransaction().begin();
        instance.update(customer);
        em.getTransaction().commit();
        
        // differs from the original
        Customer c = create();
        c.setId(customer.getId());
        Assert.assertFalse(customer.equals(c));
        
        // equals to the changed
        c.setFirstName(newName);
        Assert.assertEquals(customer, c);
    }
    
    @Test
    public void testFindById() {
        Customer customer = create();
        insert(customer);
        
        Customer c = instance.findById(customer.getId());
        
        Assert.assertEquals(customer, c);
    }
    
    @Test
    public void testRemove() {
        Customer customer = create();
        
        insert(customer);
        
        long id = customer.getId();
        
        em.getTransaction().begin();
        instance.remove(customer);
        em.getTransaction().commit();

        Customer c = instance.findById(id);
        // cannot find by id
        Assert.assertNull(c);
        // passed object was not changed
        Assert.assertNotNull(customer);
        Assert.assertNull(instance.findById(customer.getId()));
    }
    
    @Test
    public void testFindAll() {
        
        List<Customer> customers = new ArrayList();
        
        for ( Integer i = 0; i < 10; ++i ) {
            customers.add(create("Karel " + i.toString(), "blabla" ));
        }
        
        em.getTransaction().begin();
        for ( Customer c : customers ) {
            instance.persist(c);
        }
        em.getTransaction().commit();
        
        List<Customer> returnedList = instance.findAll();
        
        Assert.assertEquals(customers, returnedList);
    }
    
    private Customer create() {
        return create("aaaa", "bbbb");
    }
    private Customer create(String fName, String sName) {
        Customer customer = new Customer();
        customer.setAddress("Brno");
        customer.setLegalStatus(LegalStatus.NATURAL);
        customer.setFirstName(fName);
        customer.setSecondName(sName);
        return customer;
    }
    
    private void insert(Customer c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
}
