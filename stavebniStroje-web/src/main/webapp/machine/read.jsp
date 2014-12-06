<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="machine.detail.title">
    <s:layout-component name="body">

        <c:set var="machine" value="${actionBean.machine}"/>
        
        <div class="row bottom-small-buffer">
            <div class="col-sm-12 btn-toolbar">
                <button class="btn btn-success" data-toggle="modal" data-target="#updateMachineModal">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                    <f:message key="all.btn.edit"/>
                </button>
                <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="delete"><s:param name="machine.id" value="${machine.id}"/>
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
                        <h3 class="panel-title"><f:message key="machine.name"/></h3>
                    </div>
                    <div class="panel-body">
                        ${machine.name}
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="machine.type"/></h3>
                    </div>
                    <div class="panel-body">
                        ${machine.type}
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="machine.price"/></h3>
                    </div>
                    <div class="panel-body">
                        ${machine.price}
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><f:message key="machine.detail.state"/></h3>
                    </div>
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${machine.available}">
                                <span class="glyphicon glyphicon-ok-circle"></span>
                                <f:message key="machine.detail.available"/>
                            </c:when>
                            <c:otherwise>
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                <f:message key="machine.detail.borrowed"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><f:message key="machine.description"/></h3>
                </div>
                <div class="panel-body">
                    ${machine.description}
                </div>
            </div>
        </div>
                <h3>
                    <f:message key="machine.list.revision"/>
                </h3>
        <%@include file="../revision/list.jsp" %>


        <div class="modal fade" id="updateMachineModal" tabindex="-1" role="dialog" aria-labelledby="addMachineModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title"><f:message key="machine.list.update"/></h4>
                        </div>
                        <s:hidden name="machine.id"/>

                        <div class="modal-body">

                            <div class="form-horizontal">
                                <%@include file="form.jsp"%>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-default" data-dismis="modal"><f:message key="all.btn.cancel"/></button>
                            <s:submit name="save" class="btn btn-success"><f:message key="all.btn.save"/></s:submit>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>


    </s:layout-component>
</s:layout-render>