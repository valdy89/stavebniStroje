package cz.muni.fi.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.MachineDto; // todo change to DTO Object
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.util.Collection;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Jiří Weiser
 */
public interface MachineService {

    void newMachine(MachineDto machineDto) throws DataAccessException;

    void updateMachine(MachineDto machineDto) throws DataAccessException;
    
    void removeMachine(MachineDto machineDto) throws DataAccessException;
    
    MachineDto findMachineById(Long id) throws DataAccessException;

    Collection<MachineDto> findAllMachines() throws DataAccessException;
    
    Collection<MachineDto> findMachinesByType(MachineType type) throws DataAccessException;
    
     
}
