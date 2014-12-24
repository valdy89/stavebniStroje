package cz.muni.fi.stavebniStroje.rest.controllers;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.resources.MachineResource;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.util.ArrayList;
import java.util.List;
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
 * @author milos
 */
@RestController
@RequestMapping("/service/machine")
public class MachinesController {

    final static Logger log = LoggerFactory.getLogger(MachinesController.class);

    @Autowired
    private MachineService machineService;

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    MachineResource createMachine(@RequestBody MachineResource machineResource) {
        MachineDto machineDto = machineResource.toDto();
        machineService.newMachine(machineDto);
        return new MachineResource(machineDto);

    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    MachineResource getMachine(@PathVariable("id") Long id) {
        MachineDto machineDto = machineService.findMachineById(id);
        MachineResource mrs = new MachineResource(machineDto);
        return mrs;
    }

    @RequestMapping(value = "type/{type}", method = RequestMethod.GET)
    public @ResponseBody
    List<MachineResource> listByType(@PathVariable("type") MachineType type) {
        List<MachineResource> list = new ArrayList<>();
        for (MachineDto m : machineService.findMachinesByType(type)) {
            list.add(new MachineResource(m));
        }
        return list;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    MachineResource updateMachine(@PathVariable("id") Long id, @RequestBody MachineResource machineResource) {
        machineResource.setId(id);
        machineService.updateMachine(machineResource.toDto());
        return machineResource;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    MachineResource deleteMachine(@PathVariable("id") Long id) {
        MachineDto machineDto = machineService.findMachineById(id);
        MachineResource machineResource = new MachineResource(machineDto);
        machineService.removeMachine(machineDto);
        return machineResource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MachineResource> getAllMachines() {
        List<MachineResource> list = new ArrayList<>();
        for (MachineDto machineDto : machineService.findAllMachines()) {
            list.add(new MachineResource(machineDto));
        }
        return list;
    }
}
