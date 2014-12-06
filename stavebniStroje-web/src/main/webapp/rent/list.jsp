<%-- 
    Document   : list
    Created on : Dec 5, 2014, 6:47:13 PM
    Author     : milos
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

 <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" var="actionBean"/>

<s:layout-render name="/layout.jsp" titlekey="rent.list.title">
    <s:layout-component name="body">
        
        <h2><f:message key="rent.list.header"/></h2>
        
        <div class="row">
            <div class="col-sm-3">
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addRentModal">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <f:message key="rent.list.add"/>
                </button>
            </div>
            <div class="col-sm-9">
                <f:message key="rent.list.display"/>
                TO BE ADDED (buttons)
            </div>
        </div>
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th><f:message key="customer.list.secondName"/>, <f:message key="customer.list.firstName"/></th>
                        <th><f:message key="machine.name"/></th>
                        <th><f:message key="rent.list.from"/></th>
                        <th><f:message key="rent.list.to"/></th>                    
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${actionBean.result}" var="rent">

                        <tr>
                            <td><input type="checkbox" name="rents[]" value="${rent.id}"/></td>
                            <td>
                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" event="edit">
                                    <s:param name="customer.id" value="${rent.customer.id}"/>
                                    ${rent.customer.secondName}, ${rent.customer.firstName}
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="detail">
                                    <s:param name="machine.id" value="${rent.machine.id}"/>
                                    ${rent.machine.name}
                                </s:link>
                            </td>
                            <td>${rent.startOfRent}</td>
                            <td>${rent.endOfRent}</td>                            
                            <td>
                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" event="detail">
                                    <s:param name="rent.id" value="${rent.id}"/>
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        <f:message key="all.btn.details"/>
                                    </button>
                                </s:link>
                            </td>
                            <td>
                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" event="delete">
                                    <s:param name="rent.id" value="${rent.id}"/>
                                    <button type="button" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        <f:message key="all.btn.delete"/>
                                    </button>
                                </s:link>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <s:submit name="delete" class="btn btn-danger"><f:message key="all.btn.deleteSelected"/></s:submit>
        </s:form>

        <div class="modal fade" id="addRentModal" tabindex="-1" role="dialog" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only"><f:message key="all.btn.close"/></span>
                            </button>
                            <h4 class="modal-title"><f:message key="rent.list.add"/></h4>
                        </div>

                        <div class="modal-body">

                            <div class="form-horizontal">
                                <%@include file="form.jsp"%>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-default" data-dismis="modal"><f:message key="all.btn.cancel"/></button>
                            <s:submit name="add" class="btn btn-success"><f:message key="all.btn.save"/></s:submit>
                            </div>
                        </div>
                </s:form>
            </div>

        </div>
    </s:layout-component>
    <s:layout-component name="header">
        <script>
            $(function () {
                $('#startOfRent,#endOfRent').datepicker({ dateFormat: 'yy-mm-dd'});
            });
        </script>
    </s:layout-component>
</s:layout-render>

