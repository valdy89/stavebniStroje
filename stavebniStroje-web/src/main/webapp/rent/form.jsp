<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="cust" ><f:message key="rent.list.customer"/></s:label></th>
<td><s:select id="cust" name="customer">
        <s:options-collection collection="${actionBean.customers}" value="id" label="secondName"/>
    </s:select></td>
</tr>
<tr>
    <th><s:label for="b4" ><f:message key="rent.list.machine"/></s:label></th>
<td><s:select id="b4" name="machine">
        <s:options-collection collection="${actionBean.machines}" value="id" label="name"/>
    </s:select></td>
</tr>

<tr>
    <th><s:label for="timepicker1" ><f:message key="rent.list.from"/></s:label></th>
<td><s:text id="timepicker1" name="startOfRent"/></td>
</tr>

<tr>
    <th><s:label for="timepicker2" ><f:message key="rent.list.to"/></s:label></th>
<td><s:text id="timepicker2" name="endOfRent"/></td>
</tr>    


</table>

