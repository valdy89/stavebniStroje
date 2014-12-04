/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.mapper;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.entity.Machine;
import cz.muni.fi.stavebniStroje.entity.Rent;
import java.util.Date;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 *
 * @author spito
 */
public class MachineMapper implements CustomConverter{

    private boolean isWithinRange(Date date, Date begin, Date end) {
        return !(date.before(begin) || date.after(end));
    }
    
    private Machine machine(MachineDto source) {
        Machine m = new Machine();
        m.setId(source.getId());
        m.setName(source.getName());
        m.setPrice(source.getPrice());
        m.setRents(source.getRents());
        m.setRevisions(source.getRevisions());
        return m;
    }
    
    private MachineDto machineDto(Machine source) {
        MachineDto m = new MachineDto();
        m.setId(source.getId());
        m.setName(source.getName());
        m.setPrice(source.getPrice());
        m.setRents(source.getRents());
        m.setRevisions(source.getRevisions());
        m.setAvailable(true);
        Date now = new Date();
        for (Rent r : m.getRents()) {
            if (isWithinRange(now, r.getStartOfRent(), r.getEndOfRent())) {
                m.setAvailable(false);
                break;
            }
        }
        return m;
    }
    
    @Override
    public Object convert(Object destination, Object source, Class t1, Class t2) {
        if (source == null) {
            return null;
        }
        
        if (source instanceof Machine) {
            return machineDto((Machine)source);
        }
        if (source instanceof MachineDto){
            return machine((MachineDto)source);
        }
        throw new MappingException("Converter TestCustomConverter "
          + "used incorrectly. Arguments passed in were:"
          + destination + " and " + source);
    }
    
}
