<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" var="actionBean"/>
        <c:forEach items="${actionBean.result}" var="customer">
            ${customer.id} <br /> 
        </c:forEach>
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean">
            <fieldset>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit customerq</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>