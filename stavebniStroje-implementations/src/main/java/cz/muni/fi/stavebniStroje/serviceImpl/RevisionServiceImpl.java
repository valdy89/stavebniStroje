/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.serviceImpl;

import cz.muni.fi.stavebniStroje.dao.RevisionDao;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Revision;
import cz.muni.fi.stavebniStroje.service.RevisionService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
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
    public void newRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new IllegalArgumentException("Argument revisionDto is null");
        }
        try {
            Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
            revisionDao.persist(revision);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot persist item due to exception", ex) {
            };
        }
    }

    @Override
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
    public void removeRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new IllegalArgumentException("Argument revisionDto is null");
        }
        try {
            Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
            revisionDao.remove(revision);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot remove item due to exception", ex) {
            };
        }
    }

    @Override
    public RevisionDto findRevisionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id was null");
        }
        try {
            Revision revision;
            revision = revisionDao.findById(id);
            return dozerBeanMapper.map(revision, RevisionDto.class);
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read item due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RevisionDto> findByEndOfRevision(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument date was null");
        }
        Collection<RevisionDto> revisions = new ArrayList<>();
        try {
            for (Revision revision : revisionDao.findByDate(date)) {
                revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
            }
            return revisions;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    // nejsem si jisty zda ma byt parametr machineDto, nebo jen machine,  funkce v DAO bere jen machine
    @Override
    public Collection<RevisionDto> findRevisionByMachine(MachineDto machineDto) {
        if (machineDto == null) {
            throw new IllegalArgumentException("Argument machineDto was null");
        }
        Collection<RevisionDto> revisions = new ArrayList<>();
        try {
            for (Revision revision : revisionDao.findByMachine(dozerBeanMapper.map(machineDto, Machine.class))) {
                revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
            }
            return revisions;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

    @Override
    public Collection<RevisionDto> findAllRevision() {
        Collection<RevisionDto> revisions = new ArrayList<>();
        try {
            for (Revision revision : revisionDao.findAll()) {
                revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
            }
            return revisions;
        } catch (Exception ex) {
            throw new DataAccessException("Cannot read items due to exception", ex) {
            };
        }
    }

}
