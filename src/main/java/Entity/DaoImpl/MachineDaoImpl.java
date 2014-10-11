/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Dao.MachineDao;
import Entity.Machine;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.joda.money.BigMoney;

/**
 *
 * @author Milan
 */
public class MachineDaoImpl implements MachineDao {

    protected EntityManager entityManager;

    public MachineDaoImpl(EntityManager em) {
        entityManager = em;
    }
    
    @Override
    public void persist(Machine machine) {
       if (machine == null) {
            throw new IllegalArgumentException("Customer to be updated cannot to be null.");
        }
        entityManager.persist(machine);
        entityManager.flush();
        entityManager.refresh(machine);
    }

    @Override
    public void update(Machine machine) {
        entityManager.merge(machine);
        entityManager.persist(machine);
        entityManager.flush();
        entityManager.refresh(machine);

    }

    public void remove(Machine machine) {
        entityManager.remove(machine);
        entityManager.flush();
    }

    public Machine findById(Long id) {
        return (Machine) entityManager.find(Machine.class, id);
    }

    @Override
    public List<Machine> findAll() {
        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine");
        return (List) q.getResultList();
    }

    @Override
    public List<Machine> findByType(String type) {
        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine WHERE type = " + type.toString());
        return (List) q.getResultList();
    }

    @Override
    public List<Machine> findByRevisionDate(Date specificDate) {
        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine JOIN machine.revision  revision WHERE revision.dateOfRevision = :date_since");
        q.setParameter("date_since", specificDate);
        return (List) q.getResultList();
    }

    @Override
    public List<Machine> findByRevisionDate(Date dateFrom, Date dateTo) {

        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine JOIN machine.revision  revision WHERE revision.dateOfRevision BETWEEN :date_from AND :date_to");
        q.setParameter("date_from", dateFrom);
        q.setParameter("date_to", dateTo);
        return (List) q.getResultList();
    }

    @Override
    public List<Machine> findByPrice(BigMoney price) {
        Query q = entityManager.createQuery(
                "SELECT machine FROM Machine machine WHERE type = " + price.getAmount());
        return (List) q.getResultList();
    }

}
