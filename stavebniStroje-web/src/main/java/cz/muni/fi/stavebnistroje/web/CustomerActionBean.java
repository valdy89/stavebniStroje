/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import java.util.Collection;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 *
 * @author milos
 */
@UrlBinding("/customer/{$event}/")
public class CustomerActionBean extends BaseActionBean  {

    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(CustomerActionBean.class);
    @SpringBean
    protected CustomerService customerService;
    
    @ValidateNestedProperties({
        @Validate(on = {"add", "update", "save"}, field = "firstName", required = true),
        @Validate(on = {"add", "update", "save"}, field = "secondName", required = true),
        @Validate(on = {"add", "update", "save"}, field = "address", required = true),
        @Validate(on = {"add", "update", "save"}, field = "legalStatus", required = true),

    })
    private CustomerDto customer;
    private Collection<CustomerDto> result;
    private List<String> roles;
   
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        result = customerService.findAllCustomer();
        return new ForwardResolution("/customer/list.jsp");
    }


    public Collection<CustomerDto> getResult() {
        return result;
    }

    public void setResult(List<CustomerDto> result) {
        this.result = result;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"update", "save", "delete"})
    public void loadCustomerFromDB() {
        String id = context.getRequest().getParameter("customer.id");
        if (id != null) {
            customer = customerService.getCustomer(Long.parseLong(id));
        } else {
        }
    }    
    
    
        public Resolution redirect() {
        return new ForwardResolution("/index.jsp");
    }
        
        
    public Resolution add() {
        log.debug("add() customer={}", customer);
        try {
            //createCustomer();
            customerService.createCustomer(customer);
        } catch (InvalidDataAccessApiUsageException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        result = (List<CustomerDto>) customerService.findAllCustomer();
        return new ForwardResolution("/customer/list.jsp");
    }

    public Resolution edit() throws Exception {
        log.debug("update() customer={}", customer);
        customerService.updateCustomer(customer);
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() customer={}", customer);
        customerService.updateCustomer(customer);
        return new ForwardResolution("/customer/list.jsp");
    }

    public Resolution delete() {
        log.debug("delete({})", context.getRequest().getParameter("customer.id"));
        String id = context.getRequest().getParameter("customer.id");
        try {
            customerService.removeCustomer(customer);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new RedirectResolution("/customer/list.jsp");
    }        

    public Resolution all() {
        log.debug("all()");
        result = (List<CustomerDto>) customerService.findAllCustomer();
        return new ForwardResolution("/customer/list.jsp");
    }

    
}