/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.test.daoImpl;

import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Revision;
import java.math.BigDecimal;
import cz.muni.fi.stavebniStroje.daoImpl.RevisionDaoImpl;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milan Vallúšek
 */
public class RevisionDaoImplTest {

    @PersistenceUnit
    public EntityManagerFactory emf;

    @PersistenceUnit
    public EntityManager em;

    RevisionDaoImpl instance;
    Revision revision;
    Machine machine;

    public RevisionDaoImplTest() {
        emf = Persistence.createEntityManagerFactory("stroje-test");
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        instance = new RevisionDaoImpl(em);

        em.getTransaction().begin();
        machine = new Machine();
        machine.setType(MachineType.EXCAVATOR);
        machine.setName("Persist");
        machine.setDescription("Persis");
        machine.setPrice(new BigDecimal(1000));
        em.persist(machine);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        revision = new Revision();
        revision.setMachine(new Machine());
        revision.setDateOfRevision(cal.getTime());
        em.persist(revision);
        em.refresh(revision);
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        em.close();
    }

    /**
     * Test of persist method, of class RevisionDaoImpl.
     */
    @Test
    public void testPersist() {
        em.getTransaction().begin();
        instance.persist(revision);
        em.getTransaction().commit();
        assertTrue(revision.getId() > 0);
    }

    /**
     * Test of update method, of class RevisionDaoImpl.
     */
    @Test
    public void testUpdate() {
        em.getTransaction().begin();

        Machine machine = new Machine();
        machine.setType(MachineType.EXCAVATOR);
        machine.setName("Persist");
        machine.setDescription("Persis");
        machine.setPrice(new BigDecimal(1000));
        em.persist(machine);

        // update
        Machine m1 = revision.getMachine();
        revision.setMachine(machine);

        instance.update(revision);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Machine m2 = em.find(Revision.class, revision.getId()).getMachine();
        em.getTransaction().commit();
        // differs from the original

        assertFalse(m2.equals(m1));

        // equals to the changed
       
        assertEquals(m2, machine);
    }

    /**
     * Test of remove method, of class RevisionDaoImpl.
     */
    @Test
    public void testRemove() {
        em.getTransaction().begin();
        Revision revisionBefore = em.find(Revision.class, revision.getId());
        assertNotNull(revisionBefore);
        instance.remove(revision);
        Revision revisionNew = em.find(Revision.class, revision.getId());
        em.getTransaction().commit();

        assertNull(revisionNew);
    }

    /**
     * Test of findById method, of class RevisionDaoImpl.
     */
    @Test
    public void testFindById() {
       em.getTransaction().begin();
        Revision m = instance.findById(revision.getId());
        em.getTransaction().commit();
        assertEquals(revision, m);
    }

    /**
     * Test of findByDate method, of class RevisionDaoImpl.
     */
    @Test
    public void testFindByDate() {
     
        em.getTransaction().begin();
        Revision r1 = new Revision();
        Revision r2 = new Revision();
        r1.setDateOfRevision(null);
        
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        r1.setDateOfRevision(cal.getTime());
        
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -1);
        r2.setDateOfRevision(cal2.getTime());
        
        //and i have one revision with now date
        
        
        r1.setMachine(machine);
        r2.setMachine(machine);
        em.persist(r1);
        em.persist(r2);
        em.getTransaction().commit();
        Collection<Revision> col = instance.findByDate(new Date());
        Assert.assertEquals(1, col.size());
        Assert.assertTrue(col.contains(revision));
    }

    /**
     * Test of findByMachine method, of class RevisionDaoImpl.
     */
    @Test
    public void testFindByMachine() {
       em.getTransaction().begin();
        Revision r1 = new Revision();
      
        r1.setDateOfRevision(null);
        
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        r1.setDateOfRevision(cal.getTime());
  
        //and i have one revision with machine
        
        Machine m2 = new Machine();
        m2.setType(MachineType.EXCAVATOR);
        m2.setName("Persist5");
        m2.setDescription("Persis5");
        m2.setPrice(new BigDecimal(1000));
        em.persist(m2);
        r1.setMachine(m2);
        
        em.persist(r1);
        em.getTransaction().commit();
        Collection<Revision> col = instance.findByMachine(m2);
        Assert.assertEquals(1, col.size());
        Assert.assertTrue(col.contains(r1));
    }

    /**
     * Test of findAll method, of class RevisionDaoImpl.
     */
    @Test
    public void testFindAll() {
         Collection<Revision> expResult = new ArrayList();
        expResult.add(revision);
        em.getTransaction().begin();
        Collection<Revision> result = instance.findAll();
        em.getTransaction().commit();
        
         assertEquals(expResult, result);
    }

}
