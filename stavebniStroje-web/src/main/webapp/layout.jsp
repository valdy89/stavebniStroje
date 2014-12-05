<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
        <s:layout-component name="header"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <!-- Bootstrap základ start -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <!-- Bootstrap základ konec -->
        </head>
        <body>

            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}"><f:message key="common.buildingMachines"/></a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <!-- potrebujeme dodat class="active" pokud ma byt odkaz aktivni -->
                            <li><s:link beanclass="cz.muni.fi.stavebnistroje.web.MachineActionBean"><f:message key="navigation.machineList"/></s:link></li>
                            <li><s:link beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean"><f:message key="navigation.customerList"/></s:link></li>
                                 
                        </ul> 
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="/about.jsp"><f:message key="navigation.about"/></a></li>
                        </ul>
                    </div>
                </div>
            </nav>



           <!-- <div id="navigation">
                <ul>
                    <li><s:link href="/index.jsp"><f:message key="navigation.index"/></s:link></li>

                </ul>
            </div>  -->
           <div class="container">
               
                <s:messages/>
                <s:layout-component name="body"/>
            </div>
        </body>
    </html>
</s:layout-definition>