/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebnistroje.dto.CustomerDto;
import cz.muni.fi.stavebnistroje.dto.MachineDto;
import cz.muni.fi.stavebnistroje.dto.RentDto;
import cz.muni.fi.stavebnistroje.service.CustomerService;
import cz.muni.fi.stavebnistroje.service.MachineService;
import cz.muni.fi.stavebnistroje.service.RentService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@UrlBinding("/admin/rent/{$event}/")
public class RentActionBean extends BaseActionBean {

    @SpringBean
    protected RentService rentService;
    @SpringBean
    protected CustomerService customerService;
    @SpringBean
    protected MachineService machineService;

    private Collection<RentDto> result;
    private Collection<CustomerDto> customers;
    private Collection<MachineDto> machines;

    @ValidateNestedProperties({
        @Validate(on = {"read", "delete", "save"}, field = "id", required = true),
        @Validate(on = {"add", "save"}, field = "machine.id", required = true),
        @Validate(on = {"add", "save"}, field = "customer.id", required = true),
        @Validate(on = {"add", "save"}, field = "startOfRent", required = true),
        @Validate(on = {"add", "save"}, field = "endOfRent", required = true),})
    private RentDto rent;

    // listing restrictions
    private Long machineId;
    private Long customerId;
    private Date date;

    public Collection<CustomerDto> getCustomers() {
        return customers;
    }

    public Collection<MachineDto> getMachines() {
        return machines;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public Collection<RentDto> getResult() {
        return result;
    }

    public void setResult(List<RentDto> result) {
        this.result = result;
    }

    public RentService getRentService() {
        return rentService;
    }

    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }

    public RentDto getRent() {
        return rent;
    }

    public void setRent(RentDto rent) {
        this.rent = rent;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"detail", "save", "delete"})
    public void loadRentFromDB() {
        String id = getContext().getRequest().getParameter("rent.id");
        if (id != null) {
            rent = rentService.findRentById(Long.parseLong(id));
        } else {
        }
    }

    @DefaultHandler
    public Resolution list() {
        try {
            if (date == null) {
                result = rentService.findAllRent();
            } else {
                result = rentService.findRentByDate(date);
            }

            if (machineId != null) {
                result.retainAll(machineService.findMachineById(machineId).getRents());
            }
            if (customerId != null) {
                result.retainAll(customerService.getCustomer(customerId).getRents());
            }

            customers = customerService.findAllCustomer();
            machines = machineService.findAllMachines();
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }
        return new ForwardResolution("/admin/rent/list.jsp");
    }

    public Resolution add() {

        try {
            rentService.newRent(rent);
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }

        result = (List<RentDto>) rentService.findAllRent();
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution detail() {
        customers = customerService.findAllCustomer();
        machines = machineService.findAllMachines();
        return new ForwardResolution("/admin/rent/detail.jsp");
    }

    public Resolution save() {
        try {
            rentService.updateRent(rent);
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution(this.getClass(), "detail");
        resolution.addParameter("rent.id", rent.getId());
        return resolution;
    }

    public Resolution delete() {
        try {
            rentService.removeRent(rent);
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }

        return new RedirectResolution(this.getClass(), "list");
    }
}
