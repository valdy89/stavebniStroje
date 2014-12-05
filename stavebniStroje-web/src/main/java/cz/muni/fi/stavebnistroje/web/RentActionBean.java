/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.service.RentService;
import static cz.muni.fi.stavebnistroje.web.CustomerActionBean.log;
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
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 *
 * @author milos
 */
@UrlBinding("/rent/{$event}/")
public class RentActionBean extends BaseActionBean implements ValidationErrorHandler {

    //    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(RentActionBean.class);
    @SpringBean
    protected RentService rentService;

    private Collection<RentDto> result;

    @ValidateNestedProperties({
        @Validate(on = {"add", "update", "save"}, field = "machine", required = true),
        @Validate(on = {"add", "update", "save"}, field = "customer", required = true),
        @Validate(on = {"add", "update", "save"}, field = "startOfRent", required = true),
        @Validate(on = {"add", "update", "save"}, field = "endOfRent", required = true),})
    private RentDto rent;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        //result = rentService.findAllRent();
        return new ForwardResolution("/rent/list.jsp");
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

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadRentFromDB() {
        String id = getContext().getRequest().getParameter("rent.id");
        if (id != null) {
            rent = rentService.findRentById(Long.parseLong(id));
        } else {
        }
    }

    public Resolution add() {
        log.debug("add() rent={}", rent);

        rentService.newRent(rent);

        result = (List<RentDto>) rentService.findAllRent();
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() throws Exception {
        log.debug("update() rent={}", rent);
        rentService.updateRent(rent);
        return new ForwardResolution("/rent/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() rent={}", rent);
        rentService.updateRent(rent);
        return new RedirectResolution(this.getClass(),"list");
    }

    public Resolution delete() {
        log.debug("delete({})", getContext().getRequest().getParameter("rent.id"));
        
        rentService.removeRent(rent);

        return new RedirectResolution(this.getClass(),"list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        result = rentService.findAllRent();
        //return null to let the event handling continue
        return null;
    }

}
