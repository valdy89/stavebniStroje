/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.rest.controllers;

import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.resources.RentResource;
import cz.muni.fi.stavebnistroje.service.RentService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author spito
 */
@RestController
@RequestMapping("/service/rent")
public class RentsController {

    final static Logger log = LoggerFactory.getLogger(RentsController.class);

    @Autowired
    private RentService rentService;

    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    RentResource getRent(@PathVariable("id") Long id) {
        RentDto rentDto = rentService.findRentById(id);
        return new RentResource(rentDto);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    RentResource createRent(@RequestBody RentResource rentResource) {
        RentDto rentDto = rentResource.toDto();
        rentService.newRent(rentDto);
        return new RentResource(rentDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Collection<RentResource> listAll() {
        Collection<RentDto> rents = rentService.findAllRent();
        Collection<RentResource> rentResources = new ArrayList<>();

        for (RentDto r : rents) {
            rentResources.add(new RentResource(r));
        }
        return rentResources;
    }

    @RequestMapping(value = "date/{date}", method = RequestMethod.GET)
    public @ResponseBody
    Collection<RentResource> listByDate(@PathVariable("date") Date date) {
        Collection<RentDto> rents = rentService.findRentByDate(date);
        Collection<RentResource> rentResources = new ArrayList<>();

        for (RentDto r : rents) {
            rentResources.add(new RentResource(r));
        }
        return rentResources;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody
    RentResource updateMachine(@PathVariable("id") Long id, @RequestBody RentResource rentResource) {
        rentResource.setId(id);
        rentService.updateRent(rentResource.toDto());
        return rentResource;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    RentResource deleteMachine(@PathVariable("id") Long id) {
        RentDto rentDto = rentService.findRentById(id);
        RentResource machineResource = new RentResource(rentDto);
        rentService.removeRent(rentDto);
        return machineResource;
    }

}
