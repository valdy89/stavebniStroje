package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jiří Weiser
 */

@Service
@Transactional
public class MachineServiceImpl implements MachineService {

    private EntityManager entityManager;
    private MachineDao machineDao;
    Mapper mapper = new DozerBeanMapper();

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @Required
    public void setEMF(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    @Override
    public void newMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new NullPointerException("Argument machineDto was null");
        }
        Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
        entityManager.getTransaction();
        machineDao.persist(machine);
        entityManager.close();
    }

    @Transactional
    @Override
    public void updateMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new NullPointerException("Argument machineDto was null");
        }
        Machine machine = mapper.map(machineDto, Machine.class);
        machineDao.update(machine);
    }

    @Override
    public void removeMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new NullPointerException("Argument machineDto was null");
        }
        Machine machine = mapper.map(machineDto, Machine.class);
        machineDao.remove(machine);
    }

    @Transactional(readOnly = true)
    @Override
    public MachineDto findMachineById(Long id) {
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        Machine machine = machineDao.findById(id);
        return mapper.map(machine, MachineDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findAllMachines() {
        List<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findAll()) {
            machines.add(mapper.map(machine, MachineDto.class));
        }
        return machines;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findMachinesByType(String type) {
        if (type == null) {
            throw new NullPointerException("Argument type was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findByType(type)) {
            machines.add(mapper.map(machine, MachineDto.class));
        }
        return machines;
    }

    @Override
    public Collection<MachineDto> findMachinesByPrice(BigDecimal price) {
        if (price == null) {
            throw new NullPointerException("Argument price was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findByPrice(price)) {
            machines.add(mapper.map(machine, MachineDto.class));
        }
        return machines;
    }

}
