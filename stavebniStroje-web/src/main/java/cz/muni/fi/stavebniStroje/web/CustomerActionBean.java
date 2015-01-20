/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.web;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import java.util.Collection;
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
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author milos
 */
@UrlBinding("/admin/customer/{$event}/")
public class CustomerActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected CustomerService customerService;

    private Collection<CustomerDto> result;

    @ValidateNestedProperties({
        @Validate(on = {"delete"}, field = "id", required = true),
        @Validate(on = {"add",  "save"}, field = "firstName", required = true),
        @Validate(on = {"add",  "save"}, field = "secondName", required = true),
        @Validate(on = {"add",  "save"}, field = "address", required = true),
        @Validate(on = {"add",  "save"}, field = "legalStatus", required = true),})
    private CustomerDto customer;
    private String search;
    @DefaultHandler
    public Resolution list() {
        if(search != null)
            result = customerService.searchCustomer(search);
        else
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadCustomerFromDB() {
        String id = getContext().getRequest().getParameter("customer.id");
        if (id == null) {
            return;
        }
        customer = customerService.getCustomer(Long.parseLong(id));
    }

    
    public Resolution add() {

        customerService.createCustomer(customer);

        result = (List<CustomerDto>) customerService.findAllCustomer();
       return new RedirectResolution(this.getClass(),"list");
    }

    public Resolution edit() throws Exception {
        customerService.updateCustomer(customer);
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        customerService.updateCustomer(customer);
        return new RedirectResolution(this.getClass(),"list");
    }

    public Resolution delete() {
        customerService.removeCustomer(customer);

        return new RedirectResolution(this.getClass(),"list");
    }


    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        result = customerService.findAllCustomer();
        //return null to let the event handling continue
        return null;
    }

}
