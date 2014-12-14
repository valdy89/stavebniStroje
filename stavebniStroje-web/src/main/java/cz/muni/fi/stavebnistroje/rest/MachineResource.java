/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.rest;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 * @author milos
 */
public class MachineResource extends MachineDto {

    /**
     * 
     * @param machineDto 
     */
    public MachineResource(MachineDto machineDto) {
        setId(machineDto.getId());
        setName(machineDto.getName());
        setType(machineDto.getType());
        setDescription(machineDto.getDescription());
        setAvailable(machineDto.isAvailable());
        setPrice(machineDto.getPrice());
    }

    /**
     *  Method returns information about machine
     * @return plain text of machine description
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        return this.toString();
    }

}
