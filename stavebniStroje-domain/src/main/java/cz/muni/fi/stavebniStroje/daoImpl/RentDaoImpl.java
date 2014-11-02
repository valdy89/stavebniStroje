/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.daoImpl;

import cz.muni.fi.stavebniStroje.entity.Customer;
import cz.muni.fi.stavebniStroje.dao.RentDao;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jiri Weiser, 374154
 */
public class RentDaoImpl implements RentDao {

    protected EntityManager entityManager;

    public RentDaoImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void persist(Rent rent) {
        if (rent == null) {
            throw new IllegalArgumentException("Rent cannot be null.");
        }

        entityManager.persist(rent);
      
    }

    @Override
    public void update(Rent rent) {
        if (rent == null) {
            throw new IllegalArgumentException("Rent cannot be null.");
        }
        if (findById(rent.getId()) == null) {
            throw new IllegalArgumentException("Rent must exist before updating.");
        }
        entityManager.merge(rent);
        entityManager.persist(rent);
       
    }

    @Override
    public void remove(Rent rent) {
        if (rent == null) {
            throw new IllegalArgumentException("Rent cannot be null.");
        }
        if (findById(rent.getId()) == null) {
            throw new IllegalArgumentException("Rent must exist before updating.");
        }

        entityManager.remove(rent);
       
    }

    @Override
    public Rent findById(Long id) {
        return (Rent) entityManager.find(Rent.class, id);
    }

    @Override
    public Collection<Rent> findAll() {
        Query q = entityManager.createQuery(
                "SELECT rent FROM Rent rent");
        return q.getResultList();
    }

    @Override
    public Collection<Rent> findByCustomer(Customer customer) {
        Query q = entityManager.createQuery(
                "SELECT revision FROM Revision revision WHERE customer.id = :id");
        q.setParameter("id", customer.getId());
        return q.getResultList();
    }

    @Override
    public Collection<Rent> findByMachine(Machine machine) {
        Query q = entityManager.createQuery(
                "SELECT revision FROM Revision revision");
        return q.getResultList();
    }

    @Override
    public Collection<Rent> findByDuration(Duration duration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Rent> findByDate(Date date) {
        Query q = entityManager.createQuery(
                "SELECT rent FROM Rent rent WHERE :date BETWEEN startOfRent AND endOfRent");
        q.setParameter("date", date);
        return q.getResultList();
    }
}