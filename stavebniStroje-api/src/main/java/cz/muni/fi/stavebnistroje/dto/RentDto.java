/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.dto;

import cz.muni.fi.stavebnistroje.dto.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dominik David
 */
public class RentDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private long id = 0;
    private MachineDto machine;
    private CustomerDto customer;
    private Date startOfRent;
    private Date endOfRent;
   
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public Date getStartOfRent() {
        return startOfRent;
    }

    public void setStartOfRent(Date startOfRent) {
        this.startOfRent = startOfRent;
    }

    public Date getEndOfRent() {
        return endOfRent;
    }

    public void setEndOfRent(Date endOfRent) {
        this.endOfRent = endOfRent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 19 * hash + Objects.hashCode(this.machine);
        hash = 19 * hash + Objects.hashCode(this.customer);
        hash = 19 * hash + Objects.hashCode(this.startOfRent);
        hash = 19 * hash + Objects.hashCode(this.endOfRent);
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
        final RentDto other = (RentDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.startOfRent, other.startOfRent)) {
            return false;
        }
        if (!Objects.equals(this.endOfRent, other.endOfRent)) {
            return false;
        }
        return true;
    }
    
    
}
