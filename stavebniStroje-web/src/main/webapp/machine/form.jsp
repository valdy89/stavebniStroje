<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<s:errors/>
    <div class="row form-group">
            <div class="control-label col-sm-3">
                <s:label for="name" name="machine.name"><f:message key="machine.name"/></s:label>
            </div>
            <div class="col-sm-9">
                <s:text name="machine.name" id="name" size="40" class="form-control"/>
            </div>
    </div>
            
    <div class="row form-group">
        <div class="control-label col-sm-3">
            <s:label for="category" name="machine.type"><f:message key="machine.type"/></s:label>
            </div>
            <div class="col-sm-9">
            <s:select name="machine.type" class="form-control">
                <s:options-enumeration enum="cz.muni.fi.stavebniStroje.util.MachineType" />
            </s:select>
        </div>
    </div>

    <div class="row form-group">
        <div class="control-label col-sm-3">
        <s:label for="price" name="machine.price"><f:message key="machine.price"/></s:label>
        </div>
        <div class="col-sm-9">
            <div class="input-group">
            <s:text name="machine.price" id="price" class="form-control" />
                <span class="input-group-addon">Kc</span>
            </div>
        </div>
    </div>


    <div class="row form-group">
        <div class="control-label col-sm-3">
            <s:label for="description" name="machine.description"><f:message key="machine.description"/></s:label>
        </div>
        <div class="col-sm-9">
            <s:textarea name="machine.description" id="description" class="form-control"></s:textarea>
        </div>
    </div>

