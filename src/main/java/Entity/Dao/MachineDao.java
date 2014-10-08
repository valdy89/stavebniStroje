/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Dao;

import Entity.Machine;

/**
 *
 * @author Milan
 */
public interface MachineDao {
    
    void persist(Machine machine);
    
    void remove(Machine machine);
    
    Machine findById(Long id);
    
    Machine findByType(String type);
}
