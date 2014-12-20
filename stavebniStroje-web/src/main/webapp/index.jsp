<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <div class="page-header">
            <h1><f:message key="index.title"/></h1>
        </div>

        <ul>
            <li><s:link beanclass="cz.muni.fi.stavebniStroje.web.CustomerActionBean">test customeru</s:link></li>
            </ul>

    </s:layout-component>
</s:layout-render>
