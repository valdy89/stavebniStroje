/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.service.RevisionService;
import java.util.List;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
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
public class RevisionActionBean  extends BaseActionBean  {

    private ActionBeanContext context;
    final static Logger log = LoggerFactory.getLogger(RevisionActionBean.class);
    @SpringBean
    protected RevisionService revisionService;
    
    @ValidateNestedProperties({
        @Validate(on = {"add", "update", "save"}, field = "machine", required = true),
        @Validate(on = {"add", "update", "save"}, field = "dateOfRevision", required = true),

    })
    private RevisionDto revision;
    private List<RevisionDto> result;
    private List<String> roles;
    @Autowired
  
    


    public List<RevisionDto> getResult() {
        return result;
    }

    public void setResult(List<RevisionDto> result) {
        this.result = result;
    }

    public RevisionService getRevisionService() {
        return revisionService;
    }

    public void setRevisionrService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public RevisionDto getRevision() {
        return revision;
    }

    public void setRevision(RevisionDto revision) {
        this.revision = revision;
    }
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"update", "save", "delete"})
    public void loadCustomerFromDB() {
        String id = context.getRequest().getParameter("revision.id");
        if (id != null) {
            revision = revisionService.findRevisionById(Long.parseLong(id));
        } else {
        }
    }    
    
    @DefaultHandler
        public Resolution redirect() {
        return new ForwardResolution("/index.jsp");
    }
        
        
    public Resolution add() {
        log.debug("add() revision={}", revision);
        try {
            revisionService.newRevision(revision);
        } catch (InvalidDataAccessApiUsageException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        result = (List<RevisionDto>) revisionService.findAllRevision();
        return new ForwardResolution("/revision/add.jsp");
    }

    public Resolution edit() throws Exception {
        log.debug("update() revision={}", revision);
        revisionService.updateRevision(revision);
        return new ForwardResolution("/revision/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() customer={}", revision);
        revisionService.updateRevision(revision);
        return new ForwardResolution("/revision/list.jsp");
    }

    public Resolution delete() {
        log.debug("delete({})", context.getRequest().getParameter("revision.id"));
        String id = context.getRequest().getParameter("revision.id");
        try {
            revisionService.removeRevision(revision);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return new RedirectResolution("/revision/list.jsp");
    }        

    public Resolution all() {
        log.debug("all()");
        result = (List<RevisionDto>) revisionService.findAllRevision();
        return new ForwardResolution("/revision/list.jsp");
    }    
}
