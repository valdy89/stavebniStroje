<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="customer.address"/></th>
        <td><s:text id="b1" name="customer.address" class="form-control"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="customer.firstName"/></th>
        <td><s:text id="b2" name="customer.firstName" class="form-control"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="customer.secondName"/></th>
        <td><s:text id="b3" name="customer.secondName" class="form-control"/></td>
    </tr>
     <tr>
        <th><s:label for="b4" name="customer.legalStatus"/></th>
        <td><s:select id="b4" name="customer.legalStatus" class="form-control"><s:options-enumeration enum="cz.muni.fi.stavebniStroje.util.LegalStatus"/></s:select></td>
    </tr>
    
</table>