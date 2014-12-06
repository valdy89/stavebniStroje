<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="customer.list.title">
    <s:layout-component name="body">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="customer.list.addCustomer"/>
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
                        <td><input type="checkbox" name="customers[]" value="${customer.id}"/></td>
                        <td>${customer.firstName}</td>
                        <td> ${customer.secondName}</td>
                        <td> ${customer.address}</td>
                        <td>

                            <s:link beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" event="edit"><s:param name="customer.id" value="${customer.id}"/>
                                <button type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                <f:message key="customer.list.editBtn"/>
                                </button>
                            </s:link>
                            </td>
                            <td>
                              <s:link beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" event="delete">
                                  <s:param name="customer.id" value="${customer.id}"/>
                                <button type="button" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                <f:message key="customer.list.editBtn"/>
                                </button>
                            </s:link>
                            
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>

        <a href="#"><f:message key="all.btn.deleteSelected"/></a>

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



        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel"><f:message key="customer.list.addCustomer"/></h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <fieldset>
                                        <%@include file="form.jsp"%>                        
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <s:submit name="add" class="btn btn-success"><f:message key="customer.list.createBtn"/></s:submit>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                    </s:form>
                </div>
            </div>
        </div>




    </s:layout-component>
</s:layout-render>