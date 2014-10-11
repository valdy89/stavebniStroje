/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Customer;
import Entity.Dao.RentDao;
import Entity.Machine;
import Entity.Rent;
import java.time.Duration;
import java.time.Period;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

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
        assert rent != null : "Rent to be persisted cannot to be null.";

        entityManager.persist(rent);
        entityManager.flush();
        entityManager.refresh(rent);
    }

    @Override
    public void update(Rent rent) {
        assert rent != null : "Rent to be updated cannot to be null.";

        entityManager.merge(rent);
        entityManager.persist(rent);
        entityManager.flush();
        entityManager.refresh(rent);
    }

    @Override
    public void remove(Rent rent) {
        assert rent != null : "Rent to be removed cannot to be null.";

        entityManager.remove(rent);
        entityManager.flush();
    }

    @Override
    public Rent findById(Long id) {
        return (Rent) entityManager.find(Rent.class, id);
    }

    @Override
    public List<Rent> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rent> findByCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rent> findByMachine(Machine machine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rent> findByDuration(Duration duration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rent> findByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
