/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebniStroje.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dominik David
 * 
 * RevisionDto
 * 
 */
public class RevisionDto implements Serializable {
   
    private static final long serialVersionUID = 1L;
    private long id;
    private MachineDto machine;
    private Date dateOfRevision;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MachineDto getMachine() {
        return machine;
    }

    public void setMachine(MachineDto machine) {
        this.machine = machine;
    }

    public Date getDateOfRevision() {
        return dateOfRevision;
    }

    public void setDateOfRevision(Date dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.machine);
        hash = 83 * hash + Objects.hashCode(this.dateOfRevision);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RevisionDto other = (RevisionDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        if (!Objects.equals(this.dateOfRevision, other.dateOfRevision)) {
            return false;
        }
        return true;
    }
    
    
}
