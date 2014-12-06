/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.stavebnistroje.web;

import cz.muni.fi.stavebniStroje.dto.RevisionDto;
import cz.muni.fi.stavebniStroje.service.RevisionService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 *
 * @author milos
 */
@UrlBinding("/revision/{$event}/")
public class RevisionActionBean  extends BaseActionBean  {

    final static Logger log = LoggerFactory.getLogger(RevisionActionBean.class);
    @SpringBean
    protected RevisionService revisionService;
    
    public RevisionActionBean() {
        super("/machine/list");
    }
    
    
    @ValidateNestedProperties({
        @Validate(on = {"add"}, field = "machine.id", required = true),
        @Validate(on = {"add"}, field = "dateOfRevision", required = true),})
    private RevisionDto revision;
    
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

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete"})
    public void loadRevisionFromDB() {
        String id = getContext().getRequest().getParameter("revision.id");
        if (id != null) {
            revision = revisionService.findRevisionById(Long.parseLong(id));
        } else {
        }
    }    
    
    @DefaultHandler
    public Resolution defaultHandler() {
        return redirect("/index.jsp");
    }
        
        
    public Resolution add() {
        log.debug("add() revision={}", revision);
        try {
            revisionService.newRevision(revision);
        } catch (InvalidDataAccessApiUsageException e) {
            return new ForwardResolution("/fail/Fail.jsp");
        }
        return redirect();
    }

    public Resolution delete() {
        log.debug("delete({})", revision.getId());
        try {
            revisionService.removeRevision(revision);
        } catch (DataAccessException e) {
            return new RedirectResolution("/fail/Fail.jsp");
        }
        return redirect();
    }        

}
