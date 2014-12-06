<%-- 
    Document   : edit
    Created on : Dec 5, 2014, 6:47:53 PM
    Author     : milos
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="rent.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
            <s:hidden name="rent.id"/>
            <fieldset><legend><f:message key="rent.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="rent.edit.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>