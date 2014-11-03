package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dto.MachineDto;

import cz.muni.stavebniStroje.service.MachineService;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;


public class MachineServiceTest extends AbstractIntegrationTest {
    
    @Autowired
    private MachineService machineService;
    
    @Test
    public void testCreateNewMachine() {
        MachineDto machine = machineService.createNewMachine("Firstname", "Lastname",BigDecimal.ONE,"type");
        assertNotNull(machine.getId());
    }
    
    
    
}
