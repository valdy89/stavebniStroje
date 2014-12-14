/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.rest;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 * @author milos
 */
public class CustomerResource extends CustomerDto {


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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        return this.toString();
    }    
}
