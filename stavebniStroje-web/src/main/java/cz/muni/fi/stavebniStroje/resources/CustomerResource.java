/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerResource {

    private Long id;
    private String firstName;
    private String secondName;
    private String address;
    private LegalStatus legalStatus;
    
    private Collection<RentResource> rents = new ArrayList<>();
    
    @JsonCreator
    public CustomerResource(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("secondName") String secondName,
            @JsonProperty("legalStatus") LegalStatus legalStatus,
            @JsonProperty("address") String address) {
        setId(new Long(0));
        setFirstName(firstName);
        setSecondName(secondName);
        setAddress(address);
        setLegalStatus(legalStatus);
    }

    public CustomerResource(CustomerDto customerDto) {
        setId(customerDto.getId());
        setFirstName(customerDto.getFirstName());
        setSecondName(customerDto.getSecondName());
        setAddress(customerDto.getAddress());
        setLegalStatus(customerDto.getLegalStatus());
        for (RentDto r : customerDto.getRents()) {
            getRents().add(new RentResource(r));
        }
    }
    
    public CustomerDto toDto() {
        CustomerDto c = new CustomerDto();
        c.setId(getId());
        c.setFirstName(getFirstName());
        c.setSecondName(getSecondName());
        c.setAddress(getAddress());
        c.setLegalStatus(getLegalStatus());
        for (RentResource r : getRents()) {
            c.getRents().add(r.toDto());
        }
        return c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Collection<RentResource> getRents() {
        return rents;
    }

    public void setRents(Collection<RentResource> rents) {
        this.rents = rents;
    }

    
}
