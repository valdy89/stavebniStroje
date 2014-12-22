/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.util.LegalStatus;

/**
 * More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST 
* @author milos
 */
public class CustomerResource extends CustomerDto {

    @JsonCreator
    public CustomerResource(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("secondName") String secondName,
            @JsonProperty("legalStatus") LegalStatus legalStatus,
            @JsonProperty("address") String address){
        setFirstName(firstName);
        setSecondName(secondName);
        setAddress(address);
        setLegalStatus(legalStatus);
    }
    /**
     * Constructor for CustomerResouce class
     * @param customerDto customer which will be used
     */
    public CustomerResource(CustomerDto customerDto) {
        setId(customerDto.getId());
        setFirstName(customerDto.getFirstName());
        setSecondName(customerDto.getSecondName());
        setAddress(customerDto.getAddress());
        setLegalStatus(customerDto.getLegalStatus());
        setRents(customerDto.getRents());
    }

    
}
