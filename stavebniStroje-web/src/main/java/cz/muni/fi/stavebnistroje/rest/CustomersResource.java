package cz.muni.fi.stavebnistroje.rest;

import cz.muni.fi.stavebniStroje.dto.CustomerDto;
import cz.muni.fi.stavebniStroje.service.CustomerService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author milos
 */
@Path("/customers")
@Singleton
public class CustomersResource {

    final static Logger log = LoggerFactory.getLogger(CustomersResource.class);
    private CustomerService customerService;
    @Context
    private UriInfo context;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Path("{id}")
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
    @Path("json/{id}")
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
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") Integer id) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(new Long(id));
        customerService.removeCustomer(customerDto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{json}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerResource> getAllCustomers() {
        List<CustomerResource> list = new ArrayList<>();
        for (CustomerDto customerDto : customerService.findAllCustomer()) {
            list.add(new CustomerResource(customerDto));
        }
        return list;
    }
}
