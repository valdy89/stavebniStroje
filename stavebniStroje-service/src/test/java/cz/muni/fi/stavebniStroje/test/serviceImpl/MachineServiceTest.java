package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;

import cz.muni.fi.stavebniStroje.service.MachineService;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


public class MachineServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private MachineService machineService;

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private MachineDao stavebniStrojeMachineDao;
    


    @Before
    public void before() {
        
    }

    @After
    public void after() {

    }

    @Test
    public void testCreateNewMachine() {
         MachineDto machineDto =new MachineDto();
         machineDto.setName("machine");
         Machine machine = new Machine();
         machine.setName("machine");
         when(machineService.createNewMachine(machineDto)).getMock();
        
         MachineDto getMachine = machineService.createNewMachine(machineDto);
        
        
    }



}
