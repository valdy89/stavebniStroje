package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
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
        try {
            Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
            entityManager.getTransaction();
            machineDao.persist(machine);
            entityManager.close();
        } catch (Exception ex) {
            throw new DataAccessException("Cannot persist item due to exception", ex) {
            };
        }
    }

    @Transactional
    @Override
    public void updateMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new NullPointerException("Argument machineDto was null");
        }
        try {
            Machine machine = mapper.map(machineDto, Machine.class);
            machineDao.update(machine);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot update item due to exception", ex) {
            };
        }
    }

    @Override
    public void removeMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new NullPointerException("Argument machineDto was null");
        }
        try {
            Machine machine = mapper.map(machineDto, Machine.class);
            machineDao.remove(machine);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot remove item due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MachineDto findMachineById(Long id) {
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            Machine machine = machineDao.findById(id);
            return mapper.map(machine, MachineDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Canno read items due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findAllMachines() {
        Collection<MachineDto> machines = new ArrayList<>();
        try {
            for (Machine machine : machineDao.findAll()) {
                machines.add(mapper.map(machine, MachineDto.class));
            }
            return machines;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findMachinesByType(String type) {
        if (type == null) {
            throw new NullPointerException("Argument type was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        try {
            for (Machine machine : machineDao.findByType(type)) {
                machines.add(mapper.map(machine, MachineDto.class));
            }
            return machines;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<MachineDto> findMachinesByPrice(BigDecimal price) {
        if (price == null) {
            throw new NullPointerException("Argument price was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        try {
            for (Machine machine : machineDao.findByPrice(price)) {
                machines.add(mapper.map(machine, MachineDto.class));
            }
            return machines;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

}
