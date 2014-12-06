/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.util.Collection;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author milos
 */
@UrlBinding("/machine/{$event}/")
public class MachineActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(MachineActionBean.class);
    @SpringBean
    protected MachineService machineService;

    @ValidateNestedProperties({
        @Validate(on = {"read", "delete"}, field = "id", required = true),
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "type", required = true),
        @Validate(on = {"add", "save"}, field = "description", required = true),
        @Validate(on = {"add", "save"}, field = "price", required = true),})
    private MachineDto machine;
    private MachineType type;

    private Collection<MachineDto> result;

    public Collection<MachineDto> getResult() {
        return result;
    }

    public void setResult(Collection<MachineDto> result) {
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
    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    private String getParameterValue(String key) {
        return getContext().getRequest().getParameter(key);
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"read", "save", "delete"})
    public void loadMachineFromDB() {
        String id = getParameterValue("machine.id");
        if (id != null) {
            machine = machineService.findMachineById(Long.parseLong(id));
        }
    }

    public Resolution add() {
        log.debug("add() machine={}", machine);
        try {
            machineService.newMachine(machine);
        } catch (DataAccessException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        result = machineService.findAllMachines();
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution read() {
        log.debug("read() machine={}", machine);
        return new ForwardResolution("/machine/read.jsp");
    }

    public Resolution save() {
        log.debug("save() machine={}", machine);
        try {
            machineService.updateMachine(machine);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution(this.getClass(), "read");
        resolution.addParameter("machine.id", machine.getId());
        return resolution;
    }

    public Resolution delete() {
        log.debug("delete({})", machine.getId());
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
        try {
            
            if (type == null) {
                result = machineService.findAllMachines();
            }
            else {
                result = machineService.findMachinesByType(type);
            }
            
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new ForwardResolution("/machine/list.jsp");
    }

}
