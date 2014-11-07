package cz.muni.fi.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.MachineDto; // todo change to DTO Object
import java.math.BigDecimal;
import java.util.Collection;


/**
 *
 * @author Jiří Weiser
 */
public interface MachineService {

    void newMachine(MachineDto machineDto);

    void updateMachine(MachineDto machineDto);
    
    void removeMachine(MachineDto machineDto);
    
    MachineDto findMachineById(Long id);

    Collection<MachineDto> findAllMachines();
    
    Collection<MachineDto> findMachinesByType(String type);
    
    Collection<MachineDto> findMachinesByPrice(BigDecimal price);
    
}
