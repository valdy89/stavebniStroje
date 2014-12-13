/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebniStroje.service;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This class represent interface for Customer Service which has methods for work with the customer
 *
 * @author Milos Petrovic
 */
public interface CustomerService {

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")    
    public CustomerDto getCustomer(Long id) throws DataAccessException;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")    
    public void updateCustomer(CustomerDto customer) throws DataAccessException;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")    
    public void removeCustomer(CustomerDto customer) throws DataAccessException;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")    
    public Collection<CustomerDto> findAllCustomer() throws DataAccessException;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")    
    public Collection<CustomerDto> searchCustomer(String name) throws DataAccessException;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public CustomerDto findByUsername(String username);
}
