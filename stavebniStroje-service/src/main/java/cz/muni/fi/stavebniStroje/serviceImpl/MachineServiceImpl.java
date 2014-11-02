package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MachineServiceImpl implements MachineService {

    private MachineDao machineDao;
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<MachineDto> getAllMachines() {
        List<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findAll()) {
            machines.add(dozerBeanMapper.map(machine, MachineDto.class));
        }
        return machines;

    }

    public MachineDto createNewMachine(String name, String description, BigDecimal price, String type) {
        MachineDto machineDto = new MachineDto();
        machineDto.setDescription(description);
        machineDto.setName(name);
        machineDto.setPrice(price);
        machineDto.setType(type);
        Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
        machineDao.persist(machine);
        
        return dozerBeanMapper.map(machine, MachineDto.class);
    }

}
