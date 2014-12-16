<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" var="actionBean"/>
<c:set var="type" value="${actionBean.type}"/>

<s:layout-render name="/layout.jsp" titlekey="machine.list.title">
    <s:layout-component name="body">

        <h2><f:message key="machine.list.header"/></h2>
        <div class="row">
            <div class="col-sm-3">
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addMachineModal">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <f:message key="machine.list.add"/>
                </button>
            </div>
            <div class="col-sm-9">
                <f:message key="all.display"/>
                <div class="btn-group" role="group" aria-label="...">

                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="list" class="btn btn-default${type==null? ' active' : ''}">
                        <f:message key="machine.types.all"/>
                    </s:link>
                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="list" class="btn btn-default${type=='TRACTOR'?' active':''}">
                        <s:param name="type" value="TRACTOR"/>
                        <f:message key="machine.types.tractor"/>
                    </s:link>
                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="list" class="btn btn-default${type=='EXCAVATOR'?' active':''}">
                        <s:param name="type" value="EXCAVATOR"/>
                        <f:message key="machine.types.excavator"/>
                    </s:link>
                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="list" class="btn btn-default${type=='LORRY'?' active':''}">
                        <s:param name="type" value="LORRY"/>
                        <f:message key="machine.types.lorry"/>
                    </s:link>
                </div>
            </div>
        </div>
        <div class="row">
            <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th><f:message key="machine.name"/></th>
                            <th><f:message key="machine.type"/></th>
                            <th><f:message key="machine.description"/></th>
                            <th><f:message key="machine.price"/></th>
                            <th><f:message key="machine.detail.state"/></th>
                            <th><f:message key="all.btn.tools"/></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${actionBean.result}" var="machine">

                            <tr>
                                <td><input type="checkbox" name="machines[]" value="${machine.id}" /></td>
                                <td>${machine.name}</td>

                                <td>${machine.type}</td>
                                <td>${machine.description}</td>
                                <td>${machine.price}</td>
                                <td>
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
                                </td>
                                <td>
                                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="detail"><s:param name="machine.id" value="${machine.id}"/>
                                        <button type="button" class="btn btn-default">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                            <f:message key="all.btn.details"/>
                                        </button>
                                    </s:link>
                                    <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="delete"><s:param name="machine.id" value="${machine.id}"/>
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
        </div>


        <div class="modal fade" id="addMachineModal" tabindex="-1" role="dialog" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">
                                    <f:message key="all.btn.close"/>
                                </span>
                            </button>
                            <h4 class="modal-title"><f:message key="machine.list.add"/></h4>
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

                            <button type="reset" class="btn btn-default" data-dismiss="modal">                               
                                <f:message key="all.btn.close"/>                                
                            </button>
                        </div>
                    </div>
                </s:form>
            </div>

        </div>

    </s:layout-component>
    <s:layout-component name="header">
        <script type="text/javascript">
            $(document).ready(function() {


                if ($("#displayModalErrors").length) {
                    $('#addMachineModal').modal('show');
                }


            })
        </script>
    </s:layout-component>
</s:layout-render>
