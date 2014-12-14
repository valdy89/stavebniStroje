package cz.muni.fi.stavebnistroje.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Application config define the entrypoint. More on:
 * https://kore.fi.muni.cz/wiki/index.php/PA165/Lab_session_Webservices_REST
 *
 * @author milos
 */

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cz.muni.fi.stavebnistroje.rest.ServiceResource.class);
        resources.add(cz.muni.fi.stavebnistroje.rest.CustomerResource.class);
        resources.add(cz.muni.fi.stavebnistroje.rest.CustomersResource.class);
        resources.add(cz.muni.fi.stavebnistroje.rest.MachineResource.class);
        resources.add(cz.muni.fi.stavebnistroje.rest.MachinesResource.class);
    }
}
