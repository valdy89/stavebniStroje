package cz.muni.fi.stavebnistroje.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.MachineDao;
import cz.muni.fi.stavebnistroje.service.MachineService;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.util.MachineType;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void newMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }

        Machine machine = dozerBeanMapper.map(machineDto, Machine.class);

        machineDao.persist(machine);
        machineDto.setId(machine.getId());

    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
        machineDao.update(machine);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        Machine machine = dozerBeanMapper.map(machineDto, Machine.class);
        machineDao.remove(machine);
    }

    @Transactional(readOnly = true)
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MachineDto findMachineById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        Machine machine = machineDao.findById(id);
        return dozerBeanMapper.map(machine, MachineDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<MachineDto> findAllMachines() {
        Collection<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findAll()) {
            machines.add(dozerBeanMapper.map(machine, MachineDto.class));
        }
        return machines;
    }

    @Transactional(readOnly = true)
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<MachineDto> findMachinesByType(MachineType type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument type was null");
        }
        Collection<MachineDto> machines = new ArrayList<>();
        for (Machine machine : machineDao.findByType(type)) {
            machines.add(dozerBeanMapper.map(machine, MachineDto.class));
        }
        return machines;
    }

}
