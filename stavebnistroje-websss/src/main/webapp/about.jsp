<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="about.header" active="about">
    <s:layout-component name="body">
        <h2><f:message key="about.header"/></h2>
        
        <p>
            <f:message key="about.title"/>            
        </p>
        <h3><f:message key="about.header2"/> </h3>
        <ul>
            <li>Dominik David</li>
            <li>Miloš Petrovič</li>
            <li>Milan Valúšek</li>
            <li>Jiří Weiser</li>
        </ul>
            
            
        
     

    </s:layout-component>
</s:layout-render>
