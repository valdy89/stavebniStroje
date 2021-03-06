/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.dao;

import cz.muni.fi.stavebnistroje.entity.Customer;
import cz.muni.fi.stavebnistroje.entity.Machine;
import cz.muni.fi.stavebnistroje.entity.Rent;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Jiri Weiser, 374154
 */
public interface RentDao {

    // CRUD
    void persist(Rent rent);

    void update(Rent rent);

    void remove(Rent rent);

    Rent findById(Long id);
    
    // Other find methods
    Collection<Rent> findAll();
    
    Collection<Rent> findByDate(Date date);
}
