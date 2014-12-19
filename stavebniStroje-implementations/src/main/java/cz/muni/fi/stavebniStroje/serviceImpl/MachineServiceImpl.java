package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.MachineDao;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import org.dozer.DozerBeanMapper;
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

    private MachineDao machineDao;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @Override
    public void newMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }

        try {
            Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
            
            machineDao.persist(machine);
            
        } catch (Exception ex) {
            throw new DataAccessException("Cannot persist item due to exception", ex) {
            };
        }

    }

    @Override
    public void updateMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        try {
            Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
            machineDao.update(machine);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot update item due to exception", ex) {
            };
        }
    }

    @Override
    public void removeMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        try {
            Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
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
            throw new IllegalArgumentException("Argument id was null");
        }
        try {
            Machine machine = machineDao.findById(id);
            return dozerBeanMapper.map(machine, MachineDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findAllMachines() {
        Collection<MachineDto> machines = new ArrayList<>();
        try {
            for (Machine machine : machineDao.findAll()) {
                machines.add(dozerBeanMapper.map(machine, MachineDto.class));
            }
            return machines;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MachineDto> findMachinesByType(MachineType type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument type was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        try {
            for (Machine machine : machineDao.findByType(type)) {
                machines.add(dozerBeanMapper.map(machine, MachineDto.class));
            }
            return machines;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }


}
