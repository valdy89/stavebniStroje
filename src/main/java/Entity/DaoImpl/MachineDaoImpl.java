/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.DaoImpl;

import Entity.Dao.MachineDao;
import Entity.Machine;
import java.lang.reflect.ParameterizedType;
import javax.persistence.*;

/**
 *
 * @author Milan
 */
public class MachineDaoImpl implements MachineDao {

    protected Class entityClass;

    @PersistenceContext

    protected EntityManager entityManager;

    public MachineDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }

    public void persist(Machine machine) {
        entityManager.persist(machine);
    }

    public void remove(Machine machine) {
        entityManager.remove(machine);
    }

    public Machine findById(Long id) {
        return (Machine) entityManager.find(entityClass, id);
    }


}
