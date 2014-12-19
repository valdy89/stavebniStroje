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

        <script language="javascript">

            function getUser() {
                var id = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }
            function createUser() {

                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/create', // ukazujeme URL a
                    type: 'PUT',
                    contentType: "application/json",
                    data: JSON.stringify({"firstName": "tert", "secondName": "test", "address": "test", "legalStatus": "NATURAL"}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }
            function updateUser() {
                var id = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/delete' + id, // ukazujeme URL a
                    type: 'PUT',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }


            function getAllCustomers() {
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer', // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }

            function deleteUser() {
                var id = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/delete/' + id, // ukazujeme URL a
                    type: 'DELETE',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }
            function searchUser() {
                var search = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/delete/' + search, // ukazujeme URL a
                    type: 'DELETE',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    }
                });
            }

        </script>
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
        <h1>Machines tests</h1>

        <button onClick="createUser()">Create customer</button>
        <button onClick="getUser()">Get customer</button>
        <button onClick="deleteUser()">Delete customer</button>
        <button onClick="getAllCustomers()">Get ALL customers</button>
    </body>
</html>
