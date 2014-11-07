package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;

import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.serviceImpl.MachineServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class MachineServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private MachineService machineService = new MachineServiceImpl();

    @Mock
    private EntityManager entityManager;

    @Mock
    private MachineDao stavebniStrojeMachineDao;
    
    @Mock
    private DozerBeanMapper mapper;

    
    public MachineServiceTest() {
        mapper = new DozerBeanMapper();
     MockitoAnnotations.initMocks(this);
     ReflectionTestUtils.setField(machineService, "dozerBeanMapper", mapper);
      ReflectionTestUtils.setField(machineService, "entityManager", entityManager);
    }

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void testCreateNewMachine() {
        MachineDto machineDto = new MachineDto();
        machineDto.setName("machine");
        Machine machine = new Machine();
        machine.setName("machine");
        machineService.newMachine(machineDto);
 
        verify(stavebniStrojeMachineDao).persist(mapper.map(machineDto,Machine.class));
        assertNotNull(machine.getId());

    }

}
