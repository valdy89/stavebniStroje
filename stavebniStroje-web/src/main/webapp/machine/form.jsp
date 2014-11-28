<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


<s:label for="name" name="machine.name"></s:label>
<s:input name="machine.name" id="name" type="text" size="40"  class="form-control"/><br />

<!--  TAdy to prosim prover ty hodnoty v Options -->
<s:label for="category" name="machine.type"></s:label>
<s:select name="machine.type" class="form-control">
    <s:options-enumeration enum="cz.muni.fi.stavebniStroje.util.LegalStatus"/>
</s:select><br />

<s:label for="price" name="machine.price"></s:label>
<div class="input-group">
    <s:input name="machine.price" id="price" class="form-control" type="text" size="40"/>
    <span class="input-group-addon">Kc</span>
</div><br />


<s:label for="description" name="machine.description"></s:label>
<s:textarea name="machine.description" id="description" type="text" class="form-control"></s:textarea><br />

<s:label for="revision" name="revision.dateOfRevision"></s:label>
<s:input name="revision.dateOfRevision" id="revision" type="date" class="form-control" /><br />



