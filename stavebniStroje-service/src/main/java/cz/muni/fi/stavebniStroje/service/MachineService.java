package cz.muni.fi.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.MachineDto; // todo change to DTO Object
import java.math.BigDecimal;
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
    
    Collection<MachineDto> findMachinesByType(String type) throws DataAccessException;
    
    Collection<MachineDto> findMachinesByPrice(BigDecimal price) throws DataAccessException;
    
}
