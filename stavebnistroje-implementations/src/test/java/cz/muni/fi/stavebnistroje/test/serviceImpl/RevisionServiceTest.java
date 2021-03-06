/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.test.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.RevisionDao;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RevisionDto;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Revision;
import cz.muni.fi.stavebnistroje.service.RevisionService;
import cz.muni.fi.stavebnistroje.serviceImpl.RevisionServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Jiří Weiser
 */

public class RevisionServiceTest extends AbstractIntegrationTest {

    @InjectMocks
    private RevisionService revisionService = new RevisionServiceImpl();

    @Mock
    private RevisionDao revisionDao;

    @Autowired
    private DozerBeanMapper mapper;

    public RevisionServiceTest() {
    MockitoAnnotations.initMocks(this);
    }

    @Before
    public void before() {
       
        ReflectionTestUtils.setField(revisionService, "dozerBeanMapper", mapper);
        ReflectionTestUtils.setField(revisionService, "revisionDao", revisionDao);
    }

    @After
    public void after() {
        mapper = null;
    }

    @Test
    public void testNewRevision() {
        try {
            revisionService.newRevision(null);
            fail("Didn't throw exception when revision to be created is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Revision to be created is null.");
        }

        Revision revision = new Revision();
        revision.setDateOfRevision(getToday());

        RevisionDto revisionDto = mapper.map(revision, RevisionDto.class);
        revisionService.newRevision(revisionDto);
        verify(revisionDao).persist(revision);
    }

    @Test
    public void testUpdateRevision() {
        try {
            revisionService.updateRevision(null);
            fail("Didn't throw exception when revision to be updated is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Revision to be updated is null.");
        }

        Revision revision = new Revision();
        revision.setDateOfRevision(new Date());

        RevisionDto revisionDto = mapper.map(revision, RevisionDto.class);
        revisionService.updateRevision(revisionDto);
        verify(revisionDao).update(revision);
    }

    @Test
    public void testRemoveRevision() {
        try {
            revisionService.removeRevision(null);
            fail("Didn't throw exception when revision to be removed is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Revision to be removed is null.");
        }

        Revision revision = new Revision();
        revision.setDateOfRevision(new Date());

        RevisionDto revisionDto = mapper.map(revision, RevisionDto.class);
        revisionService.removeRevision(revisionDto);
        verify(revisionDao).remove(revision);
    }

    @Test
    public void testFindRevision() {
        try {
            revisionService.findRevisionById(null);
            fail("Didn't throw exception when id is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Id is null.");
        }

        when(revisionDao.findById(new Long(1))).thenReturn(new Revision());
        revisionService.findRevisionById(new Long(1));
        verify(revisionDao).findById(new Long(1));
    }

    @Test
    public void testFindByEndOfRevision() {

        Collection<Revision> col = new ArrayList<>();
        col.add(new Revision());
        col.add(new Revision());

        Collection<RevisionDto> expected = new ArrayList<>();
        for (Revision r : col) {
            expected.add(mapper.map(r, RevisionDto.class));
        }

        try {
            revisionService.findByEndOfRevision(null);
            fail("Didn't throw exception when date is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Date is null.");
        }
        Date today = getToday();
        when(revisionDao.findByDate(today)).thenReturn(col);
        assertEquals(expected, revisionService.findByEndOfRevision(today));
    }

    @Test
    public void testFindByMachine() {

        Collection<Revision> col = new ArrayList<>();
        col.add(new Revision());
        col.add(new Revision());

        Collection<RevisionDto> expected = new ArrayList<>();
        for (Revision r : col) {
            expected.add(mapper.map(r, RevisionDto.class));
        }

        try {
            revisionService.findRevisionByMachine(null);
            fail("Didn't throw exception when machine is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Machine is null.");
        }
        MachineDto machineDto = new MachineDto();
        Machine machine = new Machine();
        when(revisionDao.findByMachine(machine)).thenReturn(col);
        assertEquals(expected, revisionService.findRevisionByMachine(machineDto));
    }

    @Test
    public void testFindAll() {

        Collection<Revision> col = new ArrayList<>();
        col.add(new Revision());
        col.add(new Revision());

        Collection<RevisionDto> expected = new ArrayList<>();
        for (Revision r : col) {
            expected.add(mapper.map(r, RevisionDto.class));
        }

        when(revisionDao.findAll()).thenReturn(col);
        assertEquals(expected, revisionService.findAllRevision());
    }

    private Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
