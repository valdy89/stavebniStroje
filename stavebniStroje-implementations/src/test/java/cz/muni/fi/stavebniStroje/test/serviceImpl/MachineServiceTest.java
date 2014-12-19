package cz.muni.fi.stavebniStroje.test.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;

import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.serviceImpl.MachineServiceImpl;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
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
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class MachineServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private MachineService machineService;

    @Mock
    private MachineDao stavebniStrojeMachineDao;

    @Autowired
    private DozerBeanMapper mapper;

    public MachineServiceTest() {
        this.machineService = new MachineServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void before() {

        ReflectionTestUtils.setField(machineService, "dozerBeanMapper", mapper);
        ReflectionTestUtils.setField(machineService, "machineDao", stavebniStrojeMachineDao);
    }

    @After
    public void after() {
        mapper = null;
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
    public void availableMapping() {

        Date now = new Date();
        
        Rent r = new Rent();
        r.setStartOfRent(changeDay(now, -1));
        r.setEndOfRent(changeDay(now, 3));
        
        Collection<Rent> rents = new ArrayList<>();
        rents.add(r);
        
        Machine m = new Machine();
        m.setId(0);
        m.setName("test");
        m.setPrice(BigDecimal.ZERO);
        m.setRents(rents);
        r.setMachine(m);
        
        MachineDto mdto = mapper.map(m, MachineDto.class);
        Assert.assertFalse(mdto.isAvailable());
        r.setStartOfRent(changeDay(now, 1));
        mdto = mapper.map(m, MachineDto.class);
        Assert.assertTrue(mdto.isAvailable());
        Assert.assertEquals(r, mapper.map(mdto.getRents().iterator().next(), Rent.class));
    }

    @Test
    public void updateMachine() {
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
    public void removeMachine() {
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
        machines.add(machine1);
        machines.add(machine2);
        List<MachineDto> expected = new ArrayList<>();
        for (Machine m : machines) {
            expected.add(mapper.map(m, MachineDto.class));
        }

        Mockito.when(stavebniStrojeMachineDao.findAll()).thenReturn(machines);
        Assert.assertEquals(expected, machineService.findAllMachines());
        Mockito.verify(stavebniStrojeMachineDao, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindMachinesByType() {
        Machine machine1 = new Machine();
        machine1.setId(100L);
        machine1.setName("stroj");
        Machine machine2 = new Machine();
        machine2.setId(100L);
        machine2.setName("stroj");

        List<Machine> machines = new ArrayList<>();
        machines.add(machine1);
        machines.add(machine2);
        List<MachineDto> expected = new ArrayList<>();
        for (Machine m : machines) {
            expected.add(mapper.map(m, MachineDto.class));
        }

        Mockito.when(stavebniStrojeMachineDao.findByType(MachineType.EXCAVATOR)).thenReturn(machines);
        Assert.assertEquals(expected, machineService.findMachinesByType(MachineType.EXCAVATOR));
        Mockito.verify(stavebniStrojeMachineDao, Mockito.times(1)).findByType(MachineType.EXCAVATOR);
    }

    

    private Date changeDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

}
