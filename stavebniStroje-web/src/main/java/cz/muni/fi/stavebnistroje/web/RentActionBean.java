/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.RentDto;
import cz.muni.fi.stavebniStroje.service.RentService;
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
public class RentActionBean   extends BaseActionBean  {
    
        private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(RentActionBean.class);
    @SpringBean
    protected RentService rentService;    
    
    
    @ValidateNestedProperties({
        @Validate(on = {"add", "update", "save"}, field = "machine", required = true),
        @Validate(on = {"add", "update", "save"}, field = "customer", required = true),
        @Validate(on = {"add", "update", "save"}, field = "startOfRent", required = true),
        @Validate(on = {"add", "update", "save"}, field = "endOfRent", required = true),

        
    })
    private RentDto rent;
    private List<RentDto> result;
  
    @Autowired
   
    
    public List<RentDto> getResult() {
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

    public RentDto getMachine() {
        return rent;
    }

    public void setRent(RentDto rent) {
        this.rent = rent;
    }
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"update", "save", "delete"})
    public void loadRent() {
        String id = context.getRequest().getParameter("rent.id");
        if (id != null) {
            rent = rentService.findRentById(Long.parseLong(id));
        } else {
        }
    }    
    
    @DefaultHandler
        public Resolution redirect() {
        return new ForwardResolution("/index.jsp");
    }    
        
        
    public Resolution add() {
        log.debug("add() rent={}", rent);
        try {
            rentService.newRent(rent);
        } catch (InvalidDataAccessApiUsageException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        result = (List<RentDto>) rentService.findAllRent();
        return new ForwardResolution("/rent/add.jsp");
    }

    public Resolution edit() throws Exception {
        log.debug("update() rent={}", rent);
        rentService.updateRent(rent);
        return new ForwardResolution("/rent/update.jsp");
    }

    public Resolution save() {
        log.debug("save() rent={}", rent);
        rentService.updateRent(rent);
        return new ForwardResolution("/rent/list.jsp");
    }

    public Resolution delete() {
        log.debug("delete({})", context.getRequest().getParameter("rent.id"));
        String id = context.getRequest().getParameter("rent.id");
        try {
            rentService.removeRent(rent);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new RedirectResolution("/rent/list.jsp");
    }        

    public Resolution all() {
        log.debug("all()");
        result = (List<RentDto>) rentService.findAllRent();
        return new ForwardResolution("/rent/list.jsp");
    }
    
}
