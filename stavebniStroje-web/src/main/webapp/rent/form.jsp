<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:errors/>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="customer" name="rent.customer.id"><f:message key="rent.list.customer"/></s:label>
    </div>
    <div class="col-sm-9">
        <s:select id="customer" name="rent.customer.id" class="form-control">
            <s:options-collection collection="${actionBean.customers}" value="id" label="secondName"/>
        </s:select>        
    </div>
</div>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="machine" name="rent.machine.id"><f:message key="rent.list.machine"/></s:label>        
    </div>
    <div class="col-sm-9">
        <s:select id="machine" name="rent.machine.id" class="form-control">
            <s:options-collection collection="${actionBean.machines}" value="id" label="name"/>
        </s:select>        
    </div>
</div>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="startOfRent" ><f:message key="rent.list.from"/></s:label>
    </div>
    <div class="col-sm-9">
        <s:text id="startOfRent" name="rent.startOfRent" class="form-control"/>
    </div>
</div>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="endOfRent" name="rent.endOfRent"><f:message key="rent.list.to"/></s:label>        
    </div>
    <div class="col-sm-9">
        <s:text id="endOfRent" name="rent.endOfRent" class="form-control"/>
    </div>
</div>
