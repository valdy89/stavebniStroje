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
                <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" class="form-inline pull-right">
                    <f:message key="all.display"/>
                    <div class="form-group">
                        <s:select name="machineId" class="form-control">
                            <s:option value="">
                                <f:message key="rent.search.allMachines"/>
                            </s:option>
                            <s:options-collection collection="${actionBean.machines}" value="id" label="name" />
                        </s:select>

                    </div>
                    <div class="form-group">

                        <s:select name="customerId" class="form-control" >
                            <s:option value="">
                                <f:message key="rent.search.allCustomers"/>
                            </s:option>
                            <s:options-collection collection="${actionBean.customers}" value="id" label="fullName" />
                        </s:select>
                    </div>
                    <div class="form-group">

                        <s:text name="date" id="date" class="form-control"/>
                    </div>
                    <div class="form-group">

                        <s:submit name="list" class="btn btn-success">
                            <f:message key="all.btn.search"/>
                        </s:submit>
                    </div>
                </s:form>
            </div>
        </div>
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th><f:message key="rent.customer"/></th>
                        <th><f:message key="rent.machine"/></th>
                        <th><f:message key="rent.from"/></th>
                        <th><f:message key="rent.to"/></th>                    
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
            <%--
                        <s:submit name="delete" class="btn btn-danger"><f:message key="all.btn.deleteSelected"/></s:submit>
            --%>
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
                            <s:submit name="add" class="btn btn-success">
                                <f:message key="all.btn.save"/>
                            </s:submit>
                            <button type="reset" class="btn btn-default" data-dismis="modal">
                                <f:message key="all.btn.cancel"/>
                            </button>
                        </div>
                    </div>
                </s:form>
            </div>

        </div>
    </s:layout-component>
    <s:layout-component name="header">
        <script>
            $(function () {
                $('#startOfRent,#endOfRent,#date').datepicker({dateFormat: 'yy-mm-dd'});
                $('#date').attr('placeholder', '<f:message key="rent.search.date"/>');
            });
        </script>
    </s:layout-component>
</s:layout-render>

