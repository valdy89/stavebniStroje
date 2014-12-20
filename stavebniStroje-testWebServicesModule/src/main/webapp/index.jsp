<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

        <script src="${pageContext.request.contextPath}/script.js"></script>

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
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="customers.jsp">Customer tests</a></li>
                        <li><a href="machines.jsp">Machines tests</a></li>
                    </ul> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/about.jsp"><f:message key="navigation.about"/></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <h1>Test console</h1>

        <div class="alert alert-danger alert-error" id="errorAlert">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Error!</strong> A problem has been occurred during request.
        </div>

        <div class="alert" id="warnId">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Warning!</strong> The value you have just typed was not a valid id.
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Customer</h3>
            </div>
            <div class="panel-body">
                <button onClick="createCustomer()">Create customer</button>
                <button onClick="getItem('customer')">Get customer</button>
                <button onClick="deleteItem('customer')">Delete customer</button>
                <button onClick="getAllItems('customer')">Get ALL customers</button>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Machine</h3>
            </div>
            <div class="panel-body">
                <button onClick="createUser()">Create customer</button>
                <button onClick="getUser()">Get customer</button>
                <button onClick="deleteUser()">Delete customer</button>
                <button onClick="getAllCustomers()">Get ALL customers</button>
            </div>
        </div>
        

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Result</h3>
                </div>
                <div class="panel-body" id="result">
                </div>
            </div>
        </div>
        <script>
            $(function () {
                $('#errorAlert,#warnId').hide();
            });
        </script>
    </body>
</html>
