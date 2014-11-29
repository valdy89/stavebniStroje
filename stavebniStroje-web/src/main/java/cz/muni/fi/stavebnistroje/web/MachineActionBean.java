/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
import java.util.List;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 *
 * @author milos
 */
@UrlBinding("/machine/{$event}/")
public class MachineActionBean  extends BaseActionBean  {
    
    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(MachineActionBean.class);
    @SpringBean
    protected MachineService machineService;    
    
    
    @ValidateNestedProperties({
        @Validate(on = {"add", "update", "save"}, field = "name", required = true),
        @Validate(on = {"add", "update", "save"}, field = "type", required = true),
        @Validate(on = {"add", "update", "save"}, field = "description", required = true),
        @Validate(on = {"add", "update", "save"}, field = "price", required = true),
    })
    private MachineDto machine;
    private List<MachineDto> result;
    @Autowired
   
    
    public List<MachineDto> getResult() {
        return result;
    }

    public void setResult(List<MachineDto> result) {
        this.result = result;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public MachineDto getMachine() {
        return machine;
    }

    public void setMachine(MachineDto machine) {
        this.machine = machine;
    }
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"update", "save", "delete"})
    public void loadMachineFromDB() {
        String id = context.getRequest().getParameter("machine.id");
        if (id != null) {
            machine = machineService.findMachineById(Long.parseLong(id));
        } else {
        }
    }    
        
    public Resolution add() {
        log.debug("add() machine={}", machine);
/*        try {
            machineService.newMachine(machine);
        } catch (InvalidDataAccessApiUsageException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }*/
        result = (List<MachineDto>) machineService.findAllMachines();
        return new RedirectResolution(this.getClass(),"list");
    }

    public Resolution edit() {
        log.debug("update() machine={}", machine);
        machineService.updateMachine(machine);
        return new ForwardResolution("/machine/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() machine={}", machine);
        machineService.updateMachine(machine);
        return new ForwardResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        log.debug("delete({})", context.getRequest().getParameter("machine.id"));
        String id = context.getRequest().getParameter("machine.id");
        try {
            machineService.removeMachine(machine);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new RedirectResolution(this.getClass(), "list");
    }        

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        result = (List<MachineDto>) machineService.findAllMachines();
        return new ForwardResolution("/machine/list.jsp");
    }

}
