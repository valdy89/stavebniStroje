<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row bottom-small-buffer">
    <button class="btn btn-success" data-toggle="modal" data-target="#addRevisionModal">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        <f:message key="all.btn.new"/>
    </button>
</div>

        <div class="modal fade" id="addRevisionModal" tabindex="-1" role="dialog" aria-labelledby="addRevisionModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <s:form beanclass="cz.muni.fi.stavebnistroje.web.RevisionActionBean">
                        <s:hidden name="continueTo" value="/machine/read/?machine.id=${machine.id}"/>
                        <div class="modal-header">
                            <button type="reset" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only"><f:message key="all.btn.close"/></span>
                            </button>
                            <h4 class="modal-title"><f:message key="revision.new"/></h4>
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
                    </s:form>
                </div>
            </div>
        </div>

    
<ul class="list-group">
    <c:forEach items="${machine.revisions}" var="revision">

        <li class="list-group-item">
            <span class="badge">
                <s:form beanclass="cz.muni.fi.stavebnistroje.web.RevisionActionBean">
                    <s:hidden name="continueTo" value="/machine/read/?machine.id=${machine.id}"/>
                    <s:hidden name="revision.id" value="${revision.id}"/>
                    <button type="submit" name="delete">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only"><f:message key="all.btn.close"/></span>
                    </button>
                </s:form>
            </span>
            ${revision.dateOfRevision}
        </li>

    </c:forEach>
</ul>