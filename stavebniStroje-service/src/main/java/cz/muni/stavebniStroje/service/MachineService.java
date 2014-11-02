package cz.muni.stavebniStroje.service;


import cz.muni.fi.stavebniStroje.entity.Machine; // todo change to DTO Object
import java.math.BigDecimal;
import java.util.List;


public interface MachineService {
    List<Machine> getAllMachines();
    Machine createNewMachine(String name, String description, BigDecimal price, String type);
}
