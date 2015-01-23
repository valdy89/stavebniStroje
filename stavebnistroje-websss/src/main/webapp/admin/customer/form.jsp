<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:errors/>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b2" name="customer.firstName"/>
    </div>
    <div class="col-sm-9">
        <s:text id="b2" name="customer.firstName" class="form-control"/>
    </div>
</div>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b3" name="customer.secondName"/>
    </div>
    <div class="col-sm-9">
        <s:text id="b3" name="customer.secondName" class="form-control"/>
    </div>
</div>

<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b1" name="customer.address"/>
    </div>
    <div class="col-sm-9">
        <s:text id="b1" name="customer.address" class="form-control"/>
    </div>
</div>


<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b4" name="customer.legalStatus"/>
    </div>
    <div class="col-sm-9">
        <s:select id="b4" name="customer.legalStatus" class="form-control">
            <s:options-enumeration enum="cz.muni.fi.stavebnistroje.util.LegalStatus"/>
        </s:select>
    </div>
</div>
    
    <div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b7" name="customer.username"/>
    </div>
    <div class="col-sm-9">
        <s:text id="b7" name="customer.username" class="form-control"/>
    </div>
</div>
<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b5" name="customer.password"/>
    </div>
    <div class="col-sm-9">
        <s:password id="b5" name="customer.password" class="form-control"/>
    </div>
</div>
<div class="row form-group">
    <div class="control-label col-sm-3">
        <s:label for="b6" name="customer.role"/>
    </div>
    <div class="col-sm-9">
        <s:select id="b6" name="customer.role" class="form-control">
            <s:options-enumeration enum="cz.muni.fi.stavebnistroje.util.Role"/>
        </s:select>
    </div>
</div>



