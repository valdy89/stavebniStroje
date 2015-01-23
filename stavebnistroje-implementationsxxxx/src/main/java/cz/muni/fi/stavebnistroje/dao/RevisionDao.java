/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.dao;

import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Revision;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik David
 */
public interface RevisionDao {
    
    // CRUD
    void persist(Revision revision);
    
    void update(Revision revision);

    void remove(Revision revision);
    
    Revision findById(Long id);
    
    // Other find methods
    Collection<Revision> findByDate(Date date);
    
    Collection<Revision> findByMachine(Machine machine);
    
    Collection<Revision> findAll();
}
