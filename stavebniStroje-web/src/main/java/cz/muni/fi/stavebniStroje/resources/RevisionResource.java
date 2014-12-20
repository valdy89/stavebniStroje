/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author spito
 *More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 */
public class RevisionResource extends RevisionDto {

    @JsonCreator
    public RevisionResource(
            @JsonProperty("dateOfRevision") Date dateOfRevision,
            @JsonProperty("machineId") Long machineId){
        setDateOfRevision(dateOfRevision);
        getMachine().setId(machineId);
    }
    
    /**
     * 
     * @param revisionDto
     */
    public RevisionResource(RevisionDto revisionDto) {
        setId(revisionDto.getId());
        setDateOfRevision(revisionDto.getDateOfRevision());
        setMachine(revisionDto.getMachine());
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

