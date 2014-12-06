/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.daoImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.util.MachineType;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Milan
 */
public class MachineDaoImpl implements MachineDao {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    
    
    public MachineDaoImpl() { 
    }
    
    public MachineDaoImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void persist(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine to be persist cannot to be null.");
        }
        entityManager.persist(machine);
    
    }

    @Override
    public void update(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine to be update cannot to be null.");
        }
        if (findById(machine.getId()) == null) {
            throw new IllegalArgumentException("Machine must exists before updating.");
        }
        entityManager.merge(machine);
    }

    @Override
    public void remove(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine to be remove cannot to be null.");
        }
        Machine m = entityManager.find(Machine.class, machine.getId());
        if (m == null) {
            throw new IllegalArgumentException("Machine doesn't exist in the database.");
        }
        entityManager.remove(m);
    }

    @Override
    public Machine findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of machine to be find by id cannot to be null or 0.");
        }
        return (Machine) entityManager.find(Machine.class, id);
    }

    @Override
    public List<Machine> findAll() {
        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine");
        return (List) q.getResultList();
    }

    @Override
    public List<Machine> findByType(MachineType type) {
        if (type == null) {
            throw new IllegalArgumentException("Machine to be find by type cannot to be null");
        }
        TypedQuery<Machine> tq = entityManager.createQuery(
                "SELECT machine FROM Machine machine WHERE type = :type", Machine.class);
        tq.setParameter("type", type);
        return (List) tq.getResultList();
    }
/* right now postponed - not sure if we will use it
    @Override
    public List<Machine> findByRevisionDate(Date specificDate) {
        if (specificDate == null) {
            throw new IllegalArgumentException("Machine to be find by date cannot to be null");
        }
        TypedQuery<Machine> tq = entityManager.createQuery(
                "SELECT machine FROM Machine machine JOIN machine.revision  revision WHERE revision.dateOfRevision >= :date_since", Machine.class);
        tq.setParameter("date_since", specificDate);
        return (List) tq.getResultList();
    }

    @Override
    public List<Machine> findByRentDate(Date dateFrom, Date dateTo) {
        if (dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Machine to be find between dates cannot to be null");
        }
        TypedQuery<Machine> tq = entityManager.createQuery(
                "SELECT machine FROM Machine as machine JOIN machine.rent as rent WHERE rent.startOfRent BETWEEN :date_from AND :date_to OR rent.startOfRent BETWEEN :date_from AND :date_to ", Machine.class);
        tq.setParameter("date_from", dateFrom.getDate());
        tq.setParameter("date_to", dateTo.getDate());
        return (List) tq.getResultList();
    }
*/
    @Override
    public List<Machine> findByPrice(BigDecimal price) {
        TypedQuery<Machine> tq = entityManager.createQuery(
                "SELECT machine FROM Machine machine WHERE price = :price", Machine.class);
        tq.setParameter("price", price);
        return (List) tq.getResultList();
    }

   

}
