package cz.muni.fi.stavebnistroje.rest.controllers;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import cz.muni.fi.stavebnistroje.resources.CustomerResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.print.DocFlavor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author milan
 */
@RestController
@RequestMapping("/service/customer")
public class CustomersController {

    final static Logger log = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "create", method = RequestMethod.PUT)
    public @ResponseBody
    CustomerResource createCustomer(CustomerResource customerResource) {
        customerService.createCustomer(customerResource);
        return customerResource;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerResource getCustomer(@PathVariable("id") Integer id) {
        CustomerDto customerDto = customerService.getCustomer(new Long(id));
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

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public @ResponseBody
    CustomerResource updateCustomer(CustomerResource customerResource) {
        customerService.updateCustomer(customerResource);

        return customerResource;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    CustomerResource deleteCustomer(@PathVariable("id") Integer id) {
        CustomerDto customerDto = customerService.getCustomer(new Long(id));
        CustomerResource customerResource = new CustomerResource(customerDto);
        customerService.removeCustomer(customerDto);
        return customerResource;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<CustomerResource> getAllCustomers() {
        List<CustomerResource> list = new ArrayList<>();
        Collection<CustomerDto> customers = customerService.findAllCustomer();
        for (CustomerDto customerDto : customers) {
            list.add(new CustomerResource(customerDto));
        }

        return list;
    }
    @RequestMapping(value = "find/{username}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerResource findByUsername(@RequestParam("username") String username) {
        CustomerDto customerDto = customerService.findByUsername(username);
        CustomerResource crs = new CustomerResource(customerDto);
        return crs;
    }
}
