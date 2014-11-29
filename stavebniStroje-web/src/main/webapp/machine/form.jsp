<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<div class="col-md-12">

    <div class="form-group">
        <div class="control-label col-md-2">
            <s:label for="name" name="machine.name"></s:label>
        </div>
        <div class="col-md-10">
            <s:text name="machine.name" id="name" size="40" class="form-control"/>
        </div>
    </div>
    <!--  TAdy to prosim prover ty hodnoty v Options -->
    <div class="form-group">
        <div class="control-label col-md-2">
            <s:label for="category" name="machine.type"></s:label>
            </div>
            <div class="col-md-10">
            <s:select name="machine.type" class="form-control">
                <s:options-enumeration enum="cz.muni.fi.stavebniStroje.util.MachineType"/>
            </s:select>
        </div>
    </div>

    <div class="form-group">
        <div class="control-label col-md-2">
            <s:label for="price" name="machine.price"></s:label>
        </div>
        <div class="col-md-10">
            <div class="input-group form-control">
                <s:text name="machine.price" id="price" />
                <span class="input-group-addon">Kc</span>
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="control-label col-md-2">
            <s:label for="description" name="machine.description"></s:label>
        </div>
        <div class="col-md-10">
            <s:textarea name="machine.description" id="description" class="form-control"></s:textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="control-label col-md-2">
            <s:label for="revision" name="revision.dateOfRevision"></s:label>
        </div>
        <div class="col-md-10">
            <s:text name="revision.dateOfRevision" id="revision" class="form-control" />
        </div>
    </div>

</div>

