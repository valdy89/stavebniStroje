<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="customer.list.title">
    <s:layout-component name="body">
        <button type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Přidat zákazníka
        </button>

        <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" var="actionBean"/>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th><f:message key="customer.list.firstName"/></th>
                    <th><f:message key="customer.list.secondName"/></th>
                    <th><f:message key="customer.list.address"/></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>

                <c:forEach items="${actionBean.result}" var="customer">

                    <tr>
                        <td><input type="checkbox" name="stroje[]" value="${customer.id}"/></td>
                        <td>${customer.firstName}</td>
                        <td> ${customer.secondName}</td>
                        <td> ${customer.address}</td>
                        <td>
                            
                     <s:link beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" event="edit"><s:param name="customer.id" value="${customer.id}"/><f:message key="customer.list.editBtn"/></s:link>
                    
                        <td>
                            <s:form beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean">
                                 <s:hidden name="customer.id" value='${customer.id}'/>
                                <s:submit name="delete" class="btn btn-danger"><f:message key="customer.list.deleteBtn"/></s:submit>
                            </s:form>
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>

        <a href="#">Smazat vybrané</a>

<!--        <nav>
            <ul class="pagination pagination-sm navbar-right">
                <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>-->


        <h2>Přidat zákazníka</h2>
        <br />
        <div class="panel panel-default">
            <div class="panel-body">

                <s:form beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean">
                    <fieldset>
                        <%@include file="form.jsp"%>
                        <s:submit name="add"><f:message key="customer.list.createBtn"/></s:submit>
                        </fieldset>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>