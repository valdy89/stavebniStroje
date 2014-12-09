<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<s:errors/>
<s:hidden name="revision.machine.id" value="${machine.id}"/>
<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="dateOfRevision" name="revision.dateOfRevision">
            <f:message key="revision.date"/>
        </s:label>
    </div>
    <div class="col-sm-9">
        <s:text name="revision.dateOfRevision" id="dateOfRevision" size="40" class="form-control"/>
    </div>
</div>

