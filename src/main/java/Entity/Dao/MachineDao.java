/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Dao;

import Entity.Machine;
import java.util.Date;
import java.util.List;
import org.joda.money.BigMoney;

/**
 *
 * @author Milan
 */
public interface MachineDao {
    
    //CRUD
    void persist(Machine machine);
    void update(Machine machine);
    void remove(Machine machine);
    Machine findById(Long id);
    //Other find methods
    List<Machine> findByType(String type);
    List<Machine> findAll();
    List<Machine> findByRevisionDate(Date specificDate);
    List<Machine> findByRevisionDate(Date dateFrom, Date dateTo);
    List<Machine> findByPrice(BigMoney price);
}
