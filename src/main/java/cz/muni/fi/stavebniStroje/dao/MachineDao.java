/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.dao;

import cz.muni.fi.stavebniStroje.entity.Machine;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Milan Valúšek
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

    //We will not probably use it
    //List<Machine> findByRevisionDate(Date specificDate);
    //List<Machine> findByRentDate(Date dateFrom, Date dateTo);

    List<Machine> findByPrice(BigDecimal price);
}
