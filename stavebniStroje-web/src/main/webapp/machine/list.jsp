<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" var="actionBean"/>
<c:set var="type" value="${actionBean.type}"/>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h2><f:message key="machine.list.header"/></h2>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="machine.list.add"/>
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <f:message key="machine.list.display"/>
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
                                <s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean" event="read"><s:param name="machine.id" value="${machine.id}"/>
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
            <s:submit name="delete" class="btn btn-danger"><f:message key="all.btn.deleteSelected"/></s:submit>
        </s:form>
        </div>

        


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><f:message key="machine.list.add"/></h4>
      </div>
      <div class="modal-body">
          <fieldset>
              <%@include file="form.jsp"%>
</fieldset>
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-default" data-dismis="modal"><f:message key="all.btn.cancel"/></button>
                            <s:submit name="add" class="btn btn-success"><f:message key="all.btn.save"/></s:submit>
      </div>
        </s:form>
    </div>
  </div>
</div>

        
<div class="modal fade" id="addMachineModal" tabindex="-1" role="dialog" aria-hidden="true">
        
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel"><f:message key="machine.list.add"/> - TODO: make it as a modal</h4>
                    </div>
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean">

                        <div class="modal-body">

                            <div class="form-horizontal">
                                    
                            </div>
                        </div>
                        <div class="modal-footer">
                            
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