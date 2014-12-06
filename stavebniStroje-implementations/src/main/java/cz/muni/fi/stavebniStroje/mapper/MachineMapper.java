/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.mapper;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import cz.muni.fi.stavebniStroje.entity.Revision;
import cz.muni.fi.stavebniStroje.util.DateRange;
import java.util.ArrayList;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

/**
 *
 * @author spito
 */
public class MachineMapper extends DozerConverter<Machine, MachineDto> {

    // TODO: make @Autowired working
    //@Autowired
    private final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    
    public MachineMapper() {
        super(Machine.class, MachineDto.class);
    }
    
    @Override
    public MachineDto convertTo(Machine source, MachineDto destination) {
        if (source==null) {
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
    
}
