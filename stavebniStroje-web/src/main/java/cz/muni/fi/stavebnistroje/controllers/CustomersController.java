package cz.muni.fi.stavebnistroje.controllers;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import cz.muni.fi.stavebnistroje.resources.CustomerResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
@RequestMapping("/service/customer")
public class CustomersController {

    final static Logger log = LoggerFactory.getLogger(CustomersController.class);
    
   // @Autowired
    //private CustomerService customerService;
    /*
    @Context
    private UriInfo context;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Path("get/{id}")
    public CustomerResource getCustomerResource(@PathParam("id") Integer id) {
        CustomerDto customerDto = null;
        CustomerResource crs = null;
        customerDto = customerService.getCustomer(new Long(id));
        crs = new CustomerResource(customerDto);
        return crs;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerResource customerResource) {
        customerResource.setId(0L);
        customerService.createCustomer(customerResource);
        return Response.created(URI.create(context.getAbsolutePath() + "/" + customerResource.getId())).build();
    }

    @GET
    @Path("/json/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CustomerResource getCustomer(@PathParam("id") Integer id) {
        CustomerDto customerDto = customerService.getCustomer(new Long(id));
        CustomerResource crs = new CustomerResource(customerDto);
        return crs;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerResource customerResource) {
        customerService.updateCustomer(customerResource);
        return Response.created(URI.create(context.getAbsolutePath() + "json/" + customerResource.getId())).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteCustomer(@PathParam("id") Integer id) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(new Long(id));
        customerService.removeCustomer(customerDto);
        return Response.status(Response.Status.OK).build();
    }
*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<CustomerResource> getAllCustomers() {
        List<CustomerResource> list = new ArrayList<>();
        //Collection<CustomerDto> customers = customerService.findAllCustomer();
       // for (CustomerDto customerDto : customers) {
          //  list.add(new CustomerResource(customerDto));
        //
        
         return list;
    }
}
