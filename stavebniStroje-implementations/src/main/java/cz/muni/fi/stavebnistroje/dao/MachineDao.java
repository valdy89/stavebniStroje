/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.dao;

import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.util.MachineType;
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
    List<Machine> findByType(MachineType type);

    List<Machine> findAll();

    //We will not probably use it
    //List<Machine> findByRevisionDate(Date specificDate);
    //List<Machine> findByRentDate(Date dateFrom, Date dateTo);

    List<Machine> findByPrice(BigDecimal price);
}
