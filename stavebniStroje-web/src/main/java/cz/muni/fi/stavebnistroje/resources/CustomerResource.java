/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.resources;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import org.springframework.stereotype.Component;

/**
 *More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 * @author milos
 */
@Component
public class CustomerResource extends CustomerDto {

    public CustomerResource(String firstName, String secondName, LegalStatus status, String address){
        setFirstName(firstName);
        setSecondName(secondName);
        setAddress(address);
        setLegalStatus(status);
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
