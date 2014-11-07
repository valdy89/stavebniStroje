package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;

import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.serviceImpl.MachineServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

public class MachineServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private MachineService machineService = new MachineServiceImpl();

    @Mock
    private EntityManager entityManager;

    @Mock
    private MachineDao stavebniStrojeMachineDao;

    @Autowired
    private DozerBeanMapper mapper;

    public MachineServiceTest() {

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

        verify(stavebniStrojeMachineDao).persist(mapper.map(machineDto, Machine.class));
        assertNotNull(machine.getId());

    }

    @Test
    public void updateMachine() throws DataAccessException {
        try {
            machineService.updateMachine(null);
            fail("Didn't throw exception when customer to be updated is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("machine to be updated is null.");
        }

        Machine machine = new Machine();
        machine.setName("machine");
        machine.setId(100L);

        MachineDto machineDto = mapper.map(machine, MachineDto.class);
        machineService.updateMachine(machineDto);
        verify(stavebniStrojeMachineDao).update(machine);
    }

    @Test
    public void removeMachine() throws DataAccessException {
        try {
            machineService.removeMachine(null);
            fail("Didn't throw exception when machine to be removed is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Null as machine cannot be deleted.");
        }

        Machine machine = new Machine();
        machine.setName("stroj");
        machine.setId(7L);
        MachineDto remove = mapper.map(machine, MachineDto.class);
        machineService.removeMachine(remove);
        verify(stavebniStrojeMachineDao).remove(machine);
    }

    @Test
    public void findMachineByIdTest() {
        Machine machine = new Machine();
        machine.setId(100L);
        machine.setName("stroj");

        MachineDto machineDto = new MachineDto();
        machineDto.setId(100L);
        machineDto.setName("stroj");

        Long id = machine.getId();
        Mockito.when(stavebniStrojeMachineDao.findById(id)).thenReturn(machine);
        MachineDto res = machineService.findMachineById(id);
        Mockito.verify(stavebniStrojeMachineDao, Mockito.times(1)).findById(id);

    }

    @Test
    public void testFindAll() {
        
        Machine machine1 = new Machine();
        machine1.setId(100L);
        machine1.setName("stroj");
        Machine machine2 = new Machine();
        machine2.setId(100L);
        machine2.setName("stroj");
        
        List<Machine> machines = new ArrayList<>();
        List<CustomerDto> customersDto = new ArrayList<>();
        
        
        
        
        
        when(customerDao.findAll()).thenReturn(customers);
        List<CustomerDto> allCustomers = (List<CustomerDto>) customerService.findAllCustomer();
        assertEquals(allCustomers, customersDto);
    }    

    public Collection<MachineDto> findMachinesByType(String type) throws DataAccessException {
        return machineService.findMachinesByType(type);
    }

    public Collection<MachineDto> findMachinesByPrice(BigDecimal price) throws DataAccessException {
        return machineService.findMachinesByPrice(price);
    }

}
