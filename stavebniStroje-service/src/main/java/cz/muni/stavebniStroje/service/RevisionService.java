/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dominik David
 */
public interface RevisionService {
    
    void newRevision(RevisionDto revisionDto);
    
    void updateRevision(RevisionDto revisionDto);

    void removeRevision(RevisionDto revisionDto);
    
    RevisionDto findRevisionById(Long id);
    
    Collection<RevisionDto> findByEndOfRevision(Date date);
    
    Collection<RevisionDto> findRevisionByMachine(MachineDto machineDto);
    
    Collection<RevisionDto> findAllRevision();
    
}
