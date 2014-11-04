/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Revision;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik David
 */
public interface RevisionService {
    
    void newRevision(Revision revision);
    
    void updateRevision(Revision revision);

    void removeRevision(Revision revision);
    
    Revision findRevisionById(Long id);
    
    Collection<Revision> findByEndOfRevision(Date date);
    
    Collection<Revision> findRevisionByMachine(Machine machine);
    
    Collection<Revision> findAllRevision();
    
}
