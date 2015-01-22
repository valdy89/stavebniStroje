/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.MachineDao;
import cz.muni.fi.stavebnistroje.dao.RentDao;
import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Rent;
import cz.muni.fi.stavebnistroje.service.RentService;
import cz.muni.fi.stavebnistroje.util.DateRange;
import cz.muni.fi.stavebnistroje.util.DateRangeException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dominik David
 */
@Service
@Transactional
public class RentServiceImpl implements RentService {

    private RentDao rentDao;
    private MachineDao machineDao;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setRentDao(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    @Required
    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    private boolean isRentValid(Rent rent, Machine machine) {
        DateRange rentRange = new DateRange(rent.getStartOfRent(), rent.getEndOfRent());
        for (Rent r : machine.getRents()) {
            if (r.getId() == rent.getId()) {
                continue;
            }
            DateRange range = new DateRange(r.getStartOfRent(), r.getEndOfRent());
            if (range.interleave(rentRange)) {
                return false;
            }
        }

        return true;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public void newRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        Machine m = machineDao.findById(rent.getMachine().getId());
        if (rent.getMachine() != null && !isRentValid(rent, m)) {
            throw new DateRangeException();
        }

        rentDao.persist(rent);
        rentDto.setId(rent.getId());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public void updateRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        Machine m = machineDao.findById(rent.getMachine().getId());
        if (rent.getMachine() != null && !isRentValid(rent, m)) {
            throw new DateRangeException();
        }

        rentDao.update(rent);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public void removeRent(RentDto rentDto) {
        if (rentDto == null) {
            throw new IllegalArgumentException("Argument rentDto is null");
        }
        Rent rent = dozerBeanMapper.map(rentDto, Rent.class);
        rentDao.remove(rent);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public RentDto findRentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        Rent rent;
        rent = rentDao.findById(id);
        return dozerBeanMapper.map(rent, RentDto.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public Collection<RentDto> findAllRent() {
        Collection<RentDto> rents = new ArrayList<>();
        for (Rent rent : rentDao.findAll()) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
    public Collection<RentDto> findRentByDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument date was null.");
        }
        Collection<RentDto> rents = new ArrayList<>();
        for (Rent rent : rentDao.findByDate(date)) {
            rents.add(dozerBeanMapper.map(rent, RentDto.class));
        }
        return rents;
    }

}
