/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebnistroje.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.taglibs.standard.functions.Functions;

/**
 *
 * @author milos
 */
public abstract class BaseActionBean implements ActionBean {

    private ActionBeanContext context;

    private String continueTo;
    final private String defaultContinueTo;

    public BaseActionBean(String defaultContinueTo) {
        this.defaultContinueTo = defaultContinueTo;
    }
    public BaseActionBean() {
        this.defaultContinueTo = "";
    }
    
    

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }

    public String getContinueTo() {
        return continueTo;
    }

    public void setContinueTo(String continueTo) {
        this.continueTo = continueTo;
    }

    protected Resolution redirect() {
        return redirect("");
    }
    
    protected Resolution redirect(String path) {
        if (path.isEmpty()) {
            path = defaultContinueTo;
        }
        if (continueTo != null && !continueTo.isEmpty()) {
            path = continueTo;
        }

        return new RedirectResolution(path);
    }

}
