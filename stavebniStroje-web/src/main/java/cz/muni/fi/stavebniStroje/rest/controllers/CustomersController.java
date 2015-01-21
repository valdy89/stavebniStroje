package cz.muni.fi.stavebnistroje.rest.controllers;

import cz.muni.fi.stavebnistroje.dto.CustomerDto;
import cz.muni.fi.stavebnistroje.resources.CustomerResource;
import cz.muni.fi.stavebnistroje.service.CustomerService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milan
 */
@RestController
@RequestMapping("/service/customer")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    CustomerResource createCustomer(@RequestBody CustomerResource customerResource) {
        CustomerDto customerDto = customerResource.toDto();
        customerService.createCustomer(customerDto);
        return new CustomerResource(customerDto);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerResource getCustomer(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomer(id);
        CustomerResource crs = new CustomerResource(customerDto);
        return crs;
    }

    @RequestMapping(value = "search/{name}", method = RequestMethod.GET)
    public @ResponseBody List<CustomerResource> searchCustomer(@PathVariable("name") String name) {

        List<CustomerResource> list = new ArrayList<>();
        Collection<CustomerDto> customers = customerService.searchCustomer(name);
        for (CustomerDto customerDto : customers) {
            list.add(new CustomerResource(customerDto));
        }
        return list;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    CustomerResource updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerResource customerResource) {
        customerResource.setId(id);
        customerService.updateCustomer(customerResource.toDto());
        return customerResource;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    CustomerResource deleteCustomer(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomer(id);
        CustomerResource customerResource = new CustomerResource(customerDto);
        customerService.removeCustomer(customerDto);
        return customerResource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerResource> getAllCustomers() {
        List<CustomerResource> list = new ArrayList<>();
        Collection<CustomerDto> customers = customerService.findAllCustomer();
        for (CustomerDto customerDto : customers) {
            list.add(new CustomerResource(customerDto));
        }

        return list;
    }
}
