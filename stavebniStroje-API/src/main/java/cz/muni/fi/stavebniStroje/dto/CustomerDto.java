/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.dto;

import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * This class represent customer which can rent a machine
 *
 * @author Milos Petrovic
 */
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String firstName;
    private String secondName;
    private String fullName;
    private String address;
    private LegalStatus legalStatus;
    private Collection<RentDto> rents;

    /**
     * This method returns customer's ID
     *
     * @return id of the customer
     */
    public long getId() {
        return id;
    }

    /**
     * This method sets customer's ID
     *
     * @param id of the customer
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * This method returns customer's first name
     *
     * @return customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method sets customer's first name
     *
     * @param firstName - first name of the customer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        fullName = secondName + ", " + firstName;
    }

    /**
     * This method returns customer's second name
     *
     * @return customer last name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * This method set customer's first name
     *
     * @param secondName - second (last) name of the customer
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
        fullName = secondName + ", " + firstName;
    }
    
    public String getFullName() {
        return fullName;
    }

    /**
     * This method returns customer's address
     *
     * @return customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method sets customer's address
     *
     * @param address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns legal status of the customer
     *
     * @return legal status of the customer
     */
    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    /**
     * This method sets customer's address
     *
     * @param legalStatus - legal status of the customer
     */
    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Collection<RentDto> getRents() {
        return rents;
    }

    public void setRents(Collection<RentDto> rents) {
        this.rents = rents;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
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
        final CustomerDto other = (CustomerDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        return true;
    }

    /**
     * This method prints information about customer
     */
    @Override
    public String toString() {
        return "CustomerDTO{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", legalStatus=" + legalStatus + '}';
    }

}
