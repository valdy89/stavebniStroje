<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h2><f:message key="machine.list.header"/></h2>
        <br />
        <button type="button" class="btn btn-success" data-togle="modal" data-target="#addMachineModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="machine.list.add"/>
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <f:message key="machine.list.display"/>
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default active">Všechny stroje</button>
            <button type="button" class="btn btn-default">Traktory</button>
            <button type="button" class="btn btn-default">Bagry</button>
            <button type="button" class="btn btn-default">Nákladní auta</button>
        </div>

        <br />
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th><f:message key="machine.list.machineName"/></th>
                        <th><f:message key="machine.list.machineType"/></th>
                        <th><f:message key="machine.list.machineDescription"/></th>
                        <th><f:message key="machine.list.machinePrice"/></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                    <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" var="actionBean"/>

                    <c:forEach items="${actionBean.result}" var="machine">

                        <tr>
                            <td><input type="checkbox" name="machines[]" value="${machine.id}" /></td>
                            <td><a href="stranka s podrobnymi informacemi">${machine.name}</a></td>

                            <td>${machine.type}</td>
                            <td>${machine.description}</td>
                            <td>${machine.price}</td>
                            <td>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                    <f:message key="all.btn.edit"/>
                                </button>
                                <button type="button" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    <f:message key="all.btn.delete"/>
                                </button>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <s:submit name="delete" class="btn btn-danger"><f:message key="all.btn.deleteSelected"/></s:submit>
        </s:form>


        <div class="modal fade" id="addMachineModal" tabindex="-1" role="dialog" aria-labelledby="addMachineModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel"><f:message key="machine.list.add"/></h4>
                    </div>
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">

                        <div class="modal-body">

                            <div class="form-horizontal">
                                <div class="row bottom-small-buffer">
                                    <%@include file="form.jsp"%>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-default" data-dismis="modal"><f:message key="all.btn.cancel"/></button>
                            <s:submit name="add" class="btn btn-success"><f:message key="all.btn.save"/></s:submit>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>

        <nav>
            <ul class="pagination pagination-sm navbar-right">
                <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
            </ul>
        </nav>

    </s:layout-component>
</s:layout-render>