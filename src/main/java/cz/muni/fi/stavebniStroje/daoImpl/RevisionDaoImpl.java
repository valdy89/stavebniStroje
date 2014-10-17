/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.daoImpl;

import cz.muni.fi.stavebniStroje.dao.RevisionDao;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Revision;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Dominik David
 */
public class RevisionDaoImpl implements RevisionDao {

    protected EntityManager entityManager;

    public RevisionDaoImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void persist(Revision revision) {
        if (revision == null) {
            throw new IllegalArgumentException("Revision cannot be null.");
        }

        entityManager.persist(revision);
    }

    @Override
    public void update(Revision revision) {
        if (revision == null) {
            throw new IllegalArgumentException("Revision cannot be null.");
        }
        if (findById(revision.getId()) == null) {
            throw new IllegalArgumentException("Revision must exists before updating.");
        }
        entityManager.merge(revision);
    }

    @Override
    public void remove(Revision revision) {
        if (revision == null) {
            throw new IllegalArgumentException("Revision cannot be null.");
        }
        if (findById(revision.getId()) == null) {
            throw new IllegalArgumentException("Revision must exists before removing.");
        }
        entityManager.remove(revision);
    }

    @Override
    public Revision findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        return (Revision) entityManager.find(Revision.class, id);
    }

    @Override
    public Collection<Revision> findByDate(Date date) {
        Query q = entityManager.createQuery(
                "SELECT revision FROM Revision revision WHERE dateOfRevision = :date");
        q.setParameter("date", date);
        return q.getResultList();
    }

    @Override
    public Collection<Revision> findByMachine(Machine machine) {
        Query q = entityManager.createQuery(
                "SELECT revision FROM Revision revision WHERE machine.id = :id");
        q.setParameter("id", machine.getId());
        return q.getResultList();
    }

    @Override
    public Collection<Revision> findAll() {
        Query q = entityManager.createQuery(
                "SELECT revision FROM Revision revision");
        return q.getResultList();
    }

}
