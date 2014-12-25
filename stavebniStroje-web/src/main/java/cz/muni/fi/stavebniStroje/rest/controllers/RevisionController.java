/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.rest.controllers;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.resources.RevisionResource;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.service.RevisionService;
import java.util.ArrayList;
import java.util.Collection;
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
@RequestMapping("/service/revision")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @Autowired
    private MachineService machineService;

    public void setRevisionService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }
    

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    Collection<RevisionResource> getRevisions(@PathVariable("id") Long id) {
        MachineDto m = machineService.findMachineById(id);
        if (m == null) {
            return new ArrayList<>();
        }
        Collection<RevisionResource> list = new ArrayList<>();
        Collection<RevisionDto> revisions = revisionService.findRevisionByMachine(m);
        for (RevisionDto r : revisions) {
            list.add(new RevisionResource(r));
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    RevisionResource createRevision(@RequestBody RevisionResource revisionResource) {
        revisionService.newRevision(revisionResource.toDto());
        return revisionResource;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    RevisionResource deleteRevision(@PathVariable("id") Long id) {
        RevisionDto revisionDto = revisionService.findRevisionById(id);
        RevisionResource revisionResource = new RevisionResource(revisionDto);
        revisionService.removeRevision(revisionDto);
        return revisionResource;
    }
}
