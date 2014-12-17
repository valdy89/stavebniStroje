package cz.muni.fi.stavebnistroje.controllers;



/**
 *
 * @author milos
 */

public class MachinesResource {
/*
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
    }    */
}
