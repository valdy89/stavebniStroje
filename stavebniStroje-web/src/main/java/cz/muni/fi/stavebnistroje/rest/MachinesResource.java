package cz.muni.fi.stavebnistroje.rest;

import cz.muni.fi.stavebniStroje.dto.MachineDto;
import cz.muni.fi.stavebniStroje.service.MachineService;
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
@Path("/machines")
@Singleton
public class MachinesResource {

    final static Logger log = LoggerFactory.getLogger(MachinesResource.class);
    private MachineService machineService;
    @Context
    private UriInfo context;

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    @Path("{id}")
    public MachineResource getMachineResource(@PathParam("id") Integer id) {
        MachineDto machineDto = null;
        MachineResource mrs = null;
        machineDto = machineService.findMachineById(new Long(id));
        mrs = new MachineResource(machineDto);
        return mrs;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMachine(MachineResource machineResource) {
        machineResource.setId(0L);
        machineService.newMachine(machineResource);
        return Response.created(URI.create(context.getAbsolutePath() + "/" + machineResource.getId())).build();
    }

    @GET
    @Path("json/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public MachineResource getMachine(@PathParam("id") Integer id) {
        MachineDto machineDto = machineService.findMachineById(new Long(id));
        MachineResource mrs = new MachineResource(machineDto);
        return mrs;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMachine(MachineResource machineResource) {
        machineService.updateMachine(machineResource);
        return Response.created(URI.create(context.getAbsolutePath() + "json/" + machineResource.getId())).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMachine(@PathParam("id") Integer id) {
        MachineDto machineDto = new MachineDto();
        machineDto.setId(new Long(id));
        machineService.removeMachine(machineDto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{json}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MachineResource> getAllMachines() {
        List<MachineResource> list = new ArrayList<>();
        for (MachineDto machineDto : machineService.findAllMachines()) {
            list.add(new MachineResource(machineDto));
        }
        return list;
    }    
}
