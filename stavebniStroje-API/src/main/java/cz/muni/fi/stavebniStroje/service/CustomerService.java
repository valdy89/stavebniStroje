/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import java.util.Collection;
import org.springframework.dao.DataAccessException;

/**
 * This class represent interface for Customer Service which has methods for work with the customer
 *
 * @author Milos Petrovic
 */
public interface CustomerService {
    
    public void createCustomer(CustomerDto customer) throws DataAccessException;

    public CustomerDto getCustomer(Long id) throws DataAccessException;

    public void updateCustomer(CustomerDto customer) throws DataAccessException;

    public void removeCustomer(CustomerDto customer) throws DataAccessException;

    public Collection<CustomerDto> findAllCustomer() throws DataAccessException;

}
