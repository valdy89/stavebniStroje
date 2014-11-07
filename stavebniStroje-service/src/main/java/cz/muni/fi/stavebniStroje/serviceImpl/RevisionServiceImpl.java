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
import java.util.List;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dominik David
 */
public class RevisionServiceImpl implements RevisionService {
    
    private RevisionDao revisionDao;
    private EntityManager entityManager;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public void newRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new NullPointerException("Argument revisionDto is null");
        }
        
        Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
        entityManager.getTransaction();
        revisionDao.persist(revision);
        entityManager.close();
            
    }

    @Override
    public void updateRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new NullPointerException("Argument revisionDto is null");
        }
        Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
        revisionDao.update(revision);        
    }

    @Override
    public void removeRevision(RevisionDto revisionDto) {
        if (revisionDto == null) {
            throw new NullPointerException("Argument revisionDto is null");
        }
        Revision revision = dozerBeanMapper.map(revisionDto, Revision.class);
        revisionDao.remove(revision);
    }

    @Override
    public RevisionDto findRevisionById(Long id) {
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        Revision revision;
        revision = revisionDao.findById(id);
        return dozerBeanMapper.map(revision, RevisionDto.class);
    }

    @Override
    public Collection<RevisionDto> findByEndOfRevision(Date date) {
        List<RevisionDto> revisions = new ArrayList<>();
        for (Revision revision : revisionDao.findByDate(date)) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }

    // nejsem si jisty zda ma byt parametr machineDto, nebo jen machine,  funkce v DAO bere jen machine
    
    @Override
    public Collection<RevisionDto> findRevisionByMachine(MachineDto machineDto) {
        List<RevisionDto> revisions = new ArrayList<>();  
        for (Revision revision : revisionDao.findByMachine(dozerBeanMapper.map(machineDto, Machine.class))) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }

    @Override
    public Collection<RevisionDto> findAllRevision() {
        List<RevisionDto> revisions = new ArrayList<>();
        for (Revision revision : revisionDao.findAll()) {
            revisions.add(dozerBeanMapper.map(revision, RevisionDto.class));
        }
        return revisions;
    }
    
}
