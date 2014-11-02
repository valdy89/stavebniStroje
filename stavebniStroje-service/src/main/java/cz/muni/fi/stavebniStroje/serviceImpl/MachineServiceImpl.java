package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.entity.Machine;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MachineServiceImpl implements MachineService {
       
    private MachineDao machineDao;
    private EntityManagerFactory entityManagerFactory;
    
    
    @Required
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }
    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Machine> getAllMachines() {
        return machineDao.findAll();
    }

    public Machine createNewMachine(String name, String description, BigDecimal price, String type) {
        Machine machine = new Machine();
        machine.setDescription(description);
        machine.setName(name);
        machine.setPrice(price);
        machine.setType(type);
        machineDao.persist(machine);
        
        return machine;
    }
  
    
}
