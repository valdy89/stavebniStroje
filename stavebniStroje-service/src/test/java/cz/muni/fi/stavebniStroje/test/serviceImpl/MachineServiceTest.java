package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.stavebniStroje.service.MachineService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;


public class MachineServiceTest extends AbstractIntegrationTest {
    
    @Autowired
    private MachineService machineService;
    
    @Test
    public void testCreateNewMachine() {
        Machine machine = machineService.createNewMachine("Firstname", "Lastname",BigDecimal.ONE,"type");
        assertNotNull(machine.getId());
    }
    
    
    
}
