/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import java.util.Collection;
import java.util.Date;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Dominik David
 */
public interface RevisionService {
    
    void newRevision(RevisionDto revisionDto) throws DataAccessException;
    
    void updateRevision(RevisionDto revisionDto) throws DataAccessException;

    void removeRevision(RevisionDto revisionDto) throws DataAccessException;
    
    RevisionDto findRevisionById(Long id) throws DataAccessException;
    
    Collection<RevisionDto> findByEndOfRevision(Date date) throws DataAccessException;
    
    Collection<RevisionDto> findRevisionByMachine(MachineDto machineDto) throws DataAccessException;
    
    Collection<RevisionDto> findAllRevision() throws DataAccessException;
    
}
