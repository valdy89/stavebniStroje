/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.mapper;

import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.dto.RevisionDto;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Rent;
import cz.muni.fi.stavebnistroje.entity.Revision;
import cz.muni.fi.stavebnistroje.util.DateRange;
import java.util.ArrayList;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author spito
 */
public class MachineMapper extends DozerConverter<Machine, MachineDto> implements MapperAware {

    // TODO: make @Autowired working
    
    private Mapper dozerBeanMapper;

    public MachineMapper() {
        super(Machine.class, MachineDto.class);
    }


    private boolean isWithinRange(Date date, Date begin, Date end) {
        return !(date.before(begin) || date.after(end));
    }


    public MachineDto convertTo(Machine source, MachineDto destination) {
        if (source == null) {
            return null;
        }
        MachineDto m = new MachineDto();
        m.setId(source.getId());
        m.setName(source.getName());
        m.setDescription(source.getDescription());
        m.setType(source.getType());
        m.setPrice(source.getPrice());
        m.setAvailable(true);

        ArrayList<RevisionDto> revisions = new ArrayList<>(source.getRevisions().size());
        for (Revision r : source.getRevisions()) {
            revisions.add(dozerBeanMapper.map(r, RevisionDto.class));
        }
        m.setRevisions(revisions);

        Date now = new Date();
        ArrayList<RentDto> rents = new ArrayList<>(source.getRents().size());
        for (Rent r : source.getRents()) {
            rents.add(dozerBeanMapper.map(r, RentDto.class));
            DateRange range = new DateRange(r.getStartOfRent(), r.getEndOfRent());
            
            if (m.isAvailable() && range.inRange(now)) {
                m.setAvailable(false);
            }
        }
        m.setRents(rents);
        return m;
    }

    @Override
    public Machine convertFrom(MachineDto source, Machine destination) {
        if (source == null) {
            return null;
        }
        Machine m = new Machine();
        m.setId(source.getId());
        m.setName(source.getName());
        m.setDescription(source.getDescription());
        m.setType(source.getType());
        m.setPrice(source.getPrice());
        ArrayList<Rent> rents = new ArrayList<>(source.getRents().size());
        for (RentDto r : source.getRents()) {
            rents.add(dozerBeanMapper.map(r, Rent.class));
        }
        m.setRents(rents);
        ArrayList<Revision> revisions = new ArrayList<>(source.getRevisions().size());
        for (RevisionDto r : source.getRevisions()) {
            revisions.add(dozerBeanMapper.map(r, Revision.class));
        }
        m.setRevisions(revisions);
        return m;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.dozerBeanMapper = mapper;
    }

}
