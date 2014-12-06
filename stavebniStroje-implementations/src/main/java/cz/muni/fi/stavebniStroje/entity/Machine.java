/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.entity;

import cz.muni.fi.stavebniStroje.util.MachineType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Milan Valúšek
 */
@Entity
@Table(name = "Machine")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id = 0;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private MachineType type;

    @Column
    private String description;

    @OneToMany(mappedBy = "machine")
    private Collection<Rent> rents = new ArrayList<>();
    
    @OneToMany(mappedBy = "machine")
    private Collection<Revision> revisions = new ArrayList<>();

    @Column(nullable = false)
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

    public Collection<Rent> getRents() {
        return rents;
    }

    public void setRents(Collection<Rent> rents) {
        this.rents = rents;
    }

    public Collection<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(Collection<Revision> revisions) {
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
        final Machine other = (Machine) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
