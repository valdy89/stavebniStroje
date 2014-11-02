package cz.muni.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.dto.MachineDto; // todo change to DTO Object
import java.math.BigDecimal;
import java.util.List;


public interface MachineService {
    List<MachineDto> getAllMachines();
    MachineDto createNewMachine(String name, String description, BigDecimal price, String type);
}
