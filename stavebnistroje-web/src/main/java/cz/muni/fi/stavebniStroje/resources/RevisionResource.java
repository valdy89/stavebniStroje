/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RevisionDto;
import java.util.Date;

/**
 *
 * @author spito
 */
public class RevisionResource {

    private Long id;
    private Date dateOfRevision;
    private Long machineId;
    
    @JsonCreator
    public RevisionResource(
            @JsonProperty("dateOfRevision") Date dateOfRevision,
            @JsonProperty("machineId") Long machineId){
        setId(new Long(0));
        setDateOfRevision(dateOfRevision);
        setMachineId(machineId);
    }
    
    /**
     * 
     * @param revisionDto
     */
    public RevisionResource(RevisionDto revisionDto) {
        setId(revisionDto.getId());
        setDateOfRevision(revisionDto.getDateOfRevision());
        setMachineId(revisionDto.getMachine().getId());
    }
    
    public RevisionDto toDto() {
        RevisionDto r = new RevisionDto();
        r.setId(id);
        r.setDateOfRevision(dateOfRevision);
        r.setMachine(new MachineDto());
        r.getMachine().setId(machineId);
        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfRevision() {
        return dateOfRevision;
    }

    public void setDateOfRevision(Date dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
}

