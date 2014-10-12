/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Jiri Weiser, 374154
 */
@Entity
@Table(name = "Rent")
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

    @ManyToOne
    private Machine machine;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date startOfRent;

    @Temporal(TemporalType.DATE)
    private Date endOfRent;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.machine);
        hash = 41 * hash + Objects.hashCode(this.customer);
        hash = 41 * hash + Objects.hashCode(this.startOfRent);
        hash = 41 * hash + Objects.hashCode(this.endOfRent);
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
        final Rent other = (Rent) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        //if (this.startOfRent.compareTo(other.startOfRent) == 0) {
        if (!Objects.equals(this.startOfRent, other.startOfRent)) {
            return false;
        }

        return Objects.equals(this.endOfRent, other.endOfRent);
    }

}
