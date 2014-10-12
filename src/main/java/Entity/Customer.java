/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import Util.LegalStatus;

/**
 * This class represent customer which can rent a machine
 *
 * @author Milos Petrovic
 */
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "FIRSTNAME", length = 20)
    private String firstName;
    @Column(name = "SECONDNAME", length = 20)
    private String secondName;
    @Column(name = "ADDRESS", length = 100)
    private String address;
    @Column(name = "LEGALSTATUS")
    private LegalStatus legalStatus;

    /**
     * This method returns customer's ID
     */
    public long getId() {
        return id;
    }

    /**
     * This method sets customer's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * This method returns customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method set customer's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method returns customer's second/last name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * This method sets customer's second/last name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * This method returns customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method sets customer's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns customer's legal status. if he is a natural or legal
     */
    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    /**
     * This method sets customer's legal status
     */
    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    /**
     * This method create hash code for the customer
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 31 * hash + Objects.hashCode(this.firstName);
        hash = 31 * hash + Objects.hashCode(this.secondName);
        return hash;
    }

    /**
     * This method compare two instances
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (this.legalStatus != other.legalStatus) {
            return false;
        }
        return true;
    }

    /**
     * This method prints information about customer
     */
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", legalStatus=" + legalStatus + '}';
    }

}
