<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="rent.detail.title">
    <s:layout-component name="body">

        <c:set var="rent" value="${actionBean.rent}"/>

        <h2>
            <f:message key="rent.detail.header"/>
        </h2>

        <div class="row bottom-small-buffer">
            <div class="col-sm-12 btn-toolbar">
                <button class="btn btn-success" data-toggle="modal" data-target="#updateRentModal">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    <f:message key="all.btn.edit"/>
                </button>
                <s:link beanclass="cz.muni.fi.stavebniStroje.web.RentActionBean" event="delete">
                    <s:param name="rent.id" value="${rent.id}"/>
                    <button type="button" class="btn btn-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        <f:message key="all.btn.delete"/>
                    </button>
                </s:link>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="customer.list.secondName"/>, <f:message key="customer.list.firstName"/></h3>
                    </div>
                    <div class="panel-body">
                        <s:link beanclass="cz.muni.fi.stavebniStroje.web.CustomerActionBean" event="edit">
                            <s:param name="customer.id" value="${rent.customer.id}"/>
                            ${rent.customer.secondName}, ${rent.customer.firstName}
                        </s:link>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="machine.name"/></h3>
                    </div>
                    <div class="panel-body">
                        <s:link beanclass="cz.muni.fi.stavebniStroje.web.MachineActionBean" event="detail">
                            <s:param name="machine.id" value="${rent.machine.id}"/>
                            ${rent.machine.name}
                        </s:link>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="rent.from"/></h3>
                    </div>
                    <div class="panel-body">
                        ${rent.startOfRent}
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="rent.to"/></h3>
                    </div>
                    <div class="panel-body">
                        ${rent.endOfRent}
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="updateRentModal" tabindex="-1" role="dialog" aria-labelledby="addRentModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebniStroje.web.RentActionBean">
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only"><f:message key="all.btn.close"/></span>
                            </button>
                            <h4 class="modal-title"><f:message key="all.edit"/></h4>
                        </div>
                        <s:hidden name="rent.id"/>

                        <div class="modal-body">

                            <div class="form-horizontal">
                                <%@include file="form.jsp"%>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <s:submit name="save" class="btn btn-success">
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
</s:layout-render>