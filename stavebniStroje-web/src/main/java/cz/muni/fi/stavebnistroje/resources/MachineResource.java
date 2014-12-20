/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.math.BigDecimal;

/**
 *More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 * @author milos
 */
public class MachineResource extends MachineDto {

    @JsonCreator
    public MachineResource(
            @JsonProperty("name") String name,
            @JsonProperty("type") MachineType type,
            @JsonProperty("description") String description,
            @JsonProperty("price") BigDecimal price){
        setName(name);
        setType(type);
        setDescription(description);
        setPrice(price);
    }
    
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

}
