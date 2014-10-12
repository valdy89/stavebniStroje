
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Customer;
import Entity.Machine;
import Entity.Rent;
import Entity.Revision;
import Util.LegalStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing CustomerDao
 *
 * @author milos
 */
public class RentDaoImplTest {

    @PersistenceUnit
    public EntityManagerFactory emf;

    @PersistenceUnit
    public EntityManager em;

    RentDaoImpl instance;

    public RentDaoImplTest() {
        emf = Persistence.createEntityManagerFactory("stroje");
    }

    @Before
    public void before() {
        em = emf.createEntityManager();
        instance = new RentDaoImpl(em);
    }

    @After
    public void after() {
        em.close();
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
        instance.update(createRent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexitent() {
        instance.remove(createRent());
    }

    @Test
    public void testPersist() {
        Rent rent = createRent();

        insert(rent);

        Assert.assertTrue(rent.getId() > 0);
    }

    @Test
    public void testUpdate() {
        Rent rent = createRent();

        insert(rent);

        // update
        Machine m = createMachine();
        m.setName("NewName");
        // add cross references
        rent.setMachine(m);
        m.getRents().add(rent);

        MachineDaoImpl instanceM = new MachineDaoImpl(em);

        em.getTransaction().begin();
        instanceM.persist(m);
        instance.update(rent);
        em.getTransaction().commit();

        Rent r = createRent(createCustomer(), createMachine());
        r.setId(rent.getId());
        Assert.assertFalse(rent.equals(r));
    }

    @Test
    public void testFindById() {
        Rent rent = createRent();
        insert(rent);

        Rent r = instance.findById(rent.getId());

        Assert.assertEquals(rent, r);
    }

    @Test
    public void testRemove() {
        Rent rent = createRent();

        insert(rent);

        long id = rent.getId();

        em.getTransaction().begin();
        instance.remove(rent);
        em.getTransaction().commit();

        Rent c = instance.findById(id);
        // cannot find by id
        Assert.assertNull(c);
        // passed object was not changed
        Assert.assertNotNull(rent);
        Assert.assertNull(instance.findById(rent.getId()));
    }

    @Test
    public void testFindAll() {
        Collection<Rent> rents = new ArrayList();

        for (Integer i = 0; i < 10; ++i) {
            rents.add(createRent());
        }

        em.getTransaction().begin();
        for (Rent r : rents) {
            instance.persist(r);
        }
        em.getTransaction().commit();

        Collection<Rent> returnedList = instance.findAll();

        Assert.assertEquals(rents, returnedList);
    }

    @Test
    public void testFindByDate() {

        Rent rent1 = createRent();
        Rent rent2 = createRent();

        rent1.setStartOfRent(changeDay(new Date(), -3));
        rent1.setEndOfRent(changeDay(new Date(), -2));

        rent2.setStartOfRent(changeDay(new Date(), -1));
        rent2.setEndOfRent(changeDay(new Date(), 1));

        insert(rent1);
        insert(rent2);

        Collection<Rent> col = instance.findByDate(new Date());
        Assert.assertEquals(1, col.size());
        Assert.assertTrue(col.contains(rent2));
    }

    @Test
    public void testFindByCustomer() {
        Customer c = createCustomer();
        Machine m = createMachine();

    }

    private void insert(Rent rent) {
        em.getTransaction().begin();
        instance.persist(rent);
        em.getTransaction().commit();
    }

    private void insert(Collection objs) {
        CustomerDaoImpl instanceC = new CustomerDaoImpl(em);
        MachineDaoImpl instanceM = new MachineDaoImpl(em);

        em.getTransaction().begin();

        for (Object o : objs) {
            if (o.getClass() == Customer.class) {
                instanceC.persist((Customer) o);
            } else if (o.getClass() == Machine.class) {
                instanceM.persist((Machine) o);
            } else {
                throw new IllegalArgumentException("Objs must contain only Customers and Machines.");
            }
        }
        em.getTransaction().commit();
    }

    private Date changeDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    private Rent createRent() {
        Customer c = createCustomer();
        Machine m = createMachine();

        Rent r = createRent(c, m);

        Collection col = new ArrayList();
        col.add(c);
        col.add(m);

        insert(col);

        return r;
    }

    private Rent createRent(Customer c, Machine m) {
        Rent r = new Rent();
        r.setCustomer(c);
        r.setMachine(m);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        r.setEndOfRent(cal.getTime());
        r.setStartOfRent(cal.getTime());

        // add cross references
        m.getRents().add(r);

        return r;
    }

    private Customer createCustomer() {
        Customer c = new Customer();
        c.setAddress("Brno");
        c.setLegalStatus(LegalStatus.NATURAL);
        c.setFirstName("FirstName");
        c.setSecondName("SecondName");
        return c;
    }

    private Machine createMachine() {
        Machine m = new Machine();
        m.setDescription("nice machine");
        m.setName("Name");
        m.setPrice(BigDecimal.ZERO);
        m.setRents(new ArrayList<Rent>());
        m.setRevisions(new ArrayDeque<Revision>());
        m.setType("Type A");
        return m;
    }
}
