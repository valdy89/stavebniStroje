/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Dominik David
 */
@Entity
@Table(name = "revision")
public class Revision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    private Machine machine;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfRevision;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
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
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.machine);
        hash = 67 * hash + Objects.hashCode(this.dateOfRevision);
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
        final Revision other = (Revision) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        return Objects.equals(this.dateOfRevision, other.dateOfRevision);
    }

    
    
}
