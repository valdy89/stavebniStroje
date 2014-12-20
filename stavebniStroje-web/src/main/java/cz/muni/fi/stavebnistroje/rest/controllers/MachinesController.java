package cz.muni.fi.stavebniStroje.rest.controllers;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.resources.MachineResource;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping(value = "create", method = RequestMethod.PUT)
    public @ResponseBody
    MachineResource createMachine(MachineResource machineResource) {
        machineService.newMachine(machineResource);
        return machineResource;
    
    }
    

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    MachineResource getMachine(@PathParam("id") Integer id) {
        MachineDto machineDto = machineService.findMachineById(new Long(id));
        MachineResource mrs = new MachineResource(machineDto);
        return mrs;
    }    

    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public @ResponseBody
    MachineResource updateMachine(MachineResource machineResource) {
        machineService.updateMachine(machineResource);
        return machineResource;
    }    

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    MachineResource deleteMachine(@PathParam("id") Integer id) {
        MachineDto machineDto = machineService.findMachineById(new Long(id));
        MachineResource machineResource = new MachineResource(machineDto);
        machineService.removeMachine(machineDto);
        return machineResource;
    }    

    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<MachineResource> getAllMachines() {
        List<MachineResource> list = new ArrayList<>();
        for (MachineDto machineDto : machineService.findAllMachines()) {
            list.add(new MachineResource(machineDto));
        }
        return list;
    }    
}
