<%-- 
    Document   : list
    Created on : Dec 5, 2014, 6:47:13 PM
    Author     : milos
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="rent.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" var="actionBean"/>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addRentModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="rent.list.add"/>
        </button>
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th><f:message key="rent.list.id"/></th>
                        <th><f:message key="customer.list.secondName"/>, <f:message key="customer.list.firstName"/></th>
                        <th><f:message key="machine.name"/></th>
                            <%--
                            <th><f:message key="customer.list.firstName"/></th>
                            <th><f:message key="customer.list.secondName"/></th>
                           
                            <th><f:message key="machine.name"/></th>
                            <th><f:message key="machine.type"/></th>
                            --%>
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
                            <td>${rent.name}</td>
                            <td> ${customer.secondName}, ${customer.firstName}</td> 
                            <%--                        
                                <td> ${customer.firstName}</td>                        
                                <td> ${customer.secondName}</td>
                                <td> ${machine.name}</td>
                                <td> ${machine.type}</td>
                            --%>
                            <td> ${rent.startOfRent}</td>
                            <td> ${rent.endOfRent}</td>                            
                            <td>

                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean" event="edit"><s:param name="rent.id" value="${rent.id}"/><f:message key="rent.list.editBtn"/></s:link>
                                </td>
                                <td>
                                <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
                                    <s:hidden name="rent.id" value='${rent.id}'/>
                                    <s:submit name="delete" class="btn btn-danger"><f:message key="rent.list.deleteBtn"/></s:submit>
                                </s:form>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <s:submit name="delete" class="btn btn-danger"><f:message key="all.btn.deleteSelected"/></s:submit>
        </s:form>


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




        <div class="modal fade" id="addRentModal" tabindex="-1" role="dialog" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.RentActionBean">
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
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
</s:layout-render>

