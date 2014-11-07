/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.util.LegalStatus;
import java.util.List;

/**
 * This class represent interface for Customer Service which has methods for work with the customer
 *
 * @author Milos Petrovic
 */
public interface CustomerService {
    
    public void createCustomer(CustomerDto customer);

    public CustomerDto getCustomer(Long id);

    public void updateCustomer(CustomerDto customer);

    public void removeCustomer(CustomerDto customer);

    public List<CustomerDto> findAllCustomer();

}
