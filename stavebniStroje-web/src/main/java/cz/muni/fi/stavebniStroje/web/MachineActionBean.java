/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.web;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.service.RevisionService;
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
import org.springframework.dao.DataAccessException;

/**
 *
 * @author milos
 */
@UrlBinding("/admin/machine/{$event}/")
public class MachineActionBean extends BaseActionBean {

    @SpringBean
    protected MachineService machineService;

    @SpringBean
    protected RevisionService revisionService;
    
    @ValidateNestedProperties({
        @Validate(on = {"detail", "delete", "save"}, field = "id", required = true),
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "type", required = true),
        @Validate(on = {"add", "save"}, field = "description", required = true),
        @Validate(on = {"add", "save"}, field = "price", required = true),})
    private MachineDto machine;
    private MachineType type;

    @ValidateNestedProperties({
        @Validate(on = {"addRevision"}, field = "machine.id", required = true),
        @Validate(on = {"addRevision"}, field = "dateOfRevision", required = true),
        @Validate(on = {"deleteRevision"}, field = "id", required = true),})
    private RevisionDto revision;
    
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

    public RevisionService getRevisionService() {
        return revisionService;
    }

    public void setRevisionService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public MachineDto getMachine() {
        return machine;
    }

    public void setMachine(MachineDto machine) {
        this.machine = machine;
    }

    public RevisionDto getRevision() {
        return revision;
    }

    public void setRevision(RevisionDto revision) {
        this.revision = revision;
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
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"detail", "save", "delete"})
    public void loadMachineFromDB() {
        String id = getParameterValue("machine.id");
        if (id != null) {
            machine = machineService.findMachineById(Long.parseLong(id));
        }
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"deleteRevision"})
    public void loadRevisionFromDB() {
        String id = getContext().getRequest().getParameter("revision.id");
        if (id != null) {
            revision = revisionService.findRevisionById(Long.parseLong(id));
        } else {
        }
    }    
    

    public Resolution add() {
        try {
            machineService.newMachine(machine);
        } catch (DataAccessException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        result = machineService.findAllMachines();
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution detail() {
        return new ForwardResolution("/admin/machine/detail.jsp");
    }

    public Resolution save() {
        try {
            machineService.updateMachine(machine);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution("/admin/machine/detail/");
        resolution.addParameter("machine.id", machine.getId());
        return resolution;
    }
    
    public Resolution addRevision() {
        try {
            revisionService.newRevision(revision);
        } catch (DataAccessException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution("/admin/machine/detail/");
        resolution.addParameter("machine.id", revision.getMachine().getId());
        return resolution;
    }
    
    public Resolution deleteRevision() {
        try {
            revisionService.removeRevision(revision);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution("/admin/machine/detail/");
        resolution.addParameter("machine.id", revision.getMachine().getId());
        return resolution;
    }

    public Resolution delete() {
        try {
            machineService.removeMachine(machine);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @DefaultHandler
    public Resolution list() {
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
        return new ForwardResolution("/admin/machine/list.jsp");
    }

}
