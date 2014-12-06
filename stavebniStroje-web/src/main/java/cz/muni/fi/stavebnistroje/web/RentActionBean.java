/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import cz.muni.fi.stavebniStroje.service.MachineService;
import cz.muni.fi.stavebniStroje.service.RentService;
import cz.muni.fi.stavebniStroje.util.DateRangeException;
import cz.muni.fi.stavebniStroje.util.MachineType;
import java.util.ArrayList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author milos
 */
@UrlBinding("/rent/{$event}/")
public class RentActionBean extends BaseActionBean {

    //    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(RentActionBean.class);

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
        log.debug("list()");
        try {
            if (date == null) {
                result = rentService.findAllRent();
            } else {
                result = rentService.findRentByDate(date);
            }

            if (machineId != null) {
                Collection<RentDto> collection = new ArrayList<>(result.size());
                for (RentDto r : result) {
                    if (r.getMachine().getId() == machineId) {
                        collection.add(r);
                    }
                }
                result = collection;
            }

            if (customerId != null) {
                Collection<RentDto> collection = new ArrayList<>(result.size());
                for (RentDto r : result) {
                    if (r.getCustomer().getId() == machineId) {
                        collection.add(r);
                    }
                }
                result = collection;
            }

            customers = customerService.findAllCustomer();
            machines = machineService.findAllMachines();
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }
        return new ForwardResolution("/rent/list.jsp");
    }

    public Resolution add() {
        log.debug("add() rent={}", rent);

        try {
            rentService.newRent(rent);
        } catch (DateRangeException ex) {
            log.debug("cannot add rent");
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }

        result = (List<RentDto>) rentService.findAllRent();
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution detail() {
        log.debug("detail({})", rent.getId());
        customers = customerService.findAllCustomer();
        machines = machineService.findAllMachines();
        return new ForwardResolution("/rent/detail.jsp");
    }

    public Resolution save() {
        log.debug("save({})", rent.getId());
        try {
            rentService.updateRent(rent);
        } catch (DateRangeException ex) {
            log.debug("cannot add rent");
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }
        RedirectResolution resolution = new RedirectResolution(this.getClass(), "detail");
        resolution.addParameter("rent.id", rent.getId());
        return resolution;
    }

    public Resolution delete() {
        log.debug("delete({})", rent.getId());
        try {
            rentService.removeRent(rent);
        } catch (DataAccessException ex) {
            return new RedirectResolution("/fail/fail.jsp");
        }

        return new RedirectResolution(this.getClass(), "list");
    }
}
