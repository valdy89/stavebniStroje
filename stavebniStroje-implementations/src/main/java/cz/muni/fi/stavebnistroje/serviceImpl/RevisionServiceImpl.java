/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.RevisionDao;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RevisionDto;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Revision;
import cz.muni.fi.stavebnistroje.service.RevisionService;
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
public class RevisionServiceImpl implements RevisionService {

    private RevisionDao revisionDao;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Required
    public void setRevisionDao(RevisionDao revisionDao) {
        this.revisionDao = revisionDao;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void newRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new IllegalArgumentException("Argument revisionDto is null");
        }
        Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
        revisionDao.persist(revision);
        revisionDto.setId(revision.getId());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new IllegalArgumentException("Argument revisionDto is null");
        }
        try {
            Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
            revisionDao.update(revision);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot update item due to exception", ex) {
            };
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new IllegalArgumentException("Argument revisionDto is null");
        }
        Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
        revisionDao.remove(revision);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RevisionDto findRevisionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        Revision revision;
        revision = revisionDao.findById(id);
        return dozerBeanMapper.map(revision, RevisionDto.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<RevisionDto> findByEndOfRevision(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument date was null");
        }
        Collection<RevisionDto> revisions = new ArrayList<>();
        for (Revision revision : revisionDao.findByDate(date)) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }

    // nejsem si jisty zda ma byt parametr machineDto, nebo jen machine,  funkce v DAO bere jen machine
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<RevisionDto> findRevisionByMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        Collection<RevisionDto> revisions = new ArrayList<>();
        for (Revision revision : revisionDao.findByMachine(dozerBeanMapper.map(machineDto, Machine.class))) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<RevisionDto> findAllRevision() {
        Collection<RevisionDto> revisions = new ArrayList<>();
        for (Revision revision : revisionDao.findAll()) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }

}
