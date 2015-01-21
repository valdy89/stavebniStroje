/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.dto;

import cz.muni.fi.stavebnistroje.util.MachineType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Milan Valúšek
 */

public class MachineDto implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private long id = 0;

    private String name;

    private MachineType type;

    private String description;
    
    private boolean available;

    private Collection<RentDto> rents = new ArrayList<>();
   
    private Collection<RevisionDto> revisions = new ArrayList<>();

    private BigDecimal price;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Collection<RentDto> getRents() {
        return rents;
    }

    public void setRents(Collection<RentDto> rents) {
        this.rents = rents;
    }

    public Collection<RevisionDto> getRevisions() {
        return revisions;
    }

    public void setRevisions(Collection<RevisionDto> revisions) {
        this.revisions = revisions;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.name);
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
        final MachineDto other = (MachineDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
