/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.daoImpl;

import cz.muni.fi.stavebnistroje.entity.Customer;
import cz.muni.fi.stavebnistroje.dao.RentDao;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Rent;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jiri Weiser, 374154
 */
public class RentDaoImpl implements RentDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public RentDaoImpl(EntityManager em) {
        entityManager = em;
    }

    public RentDaoImpl() {
    }

    @Override
    public void persist(Rent rent) {
        if (rent == null) {
            throw new IllegalArgumentException("Rent cannot be null.");
        }
        if (rent.getCustomer() != null) {
            rent.setCustomer(entityManager.getReference(Customer.class, rent.getCustomer().getId()));
        }
        if (rent.getMachine() != null) {
            rent.setMachine(entityManager.getReference(Machine.class, rent.getMachine().getId()));
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
    }

    @Override
    public void remove(Rent rent) {
        if (rent == null) {
            throw new IllegalArgumentException("Rent cannot be null.");
        }
        Rent r = entityManager.find(Rent.class, rent.getId());
        if (r == null) {
            throw new IllegalArgumentException("Rent must exist before updating.");
        }
        entityManager.remove(r);
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
    public Collection<Rent> findByDate(Date date) {
        Query q = entityManager.createQuery(
                "SELECT rent FROM Rent rent WHERE :date BETWEEN startOfRent AND endOfRent");
        q.setParameter("date", date);
        return q.getResultList();
    }
}
