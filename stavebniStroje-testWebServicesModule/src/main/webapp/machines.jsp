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
            var items;
            function fail() {
                $('#alert').show();
            }
            function getMachine() {
                var id = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                    },
                    error: fail
                });
            }

            function flushList(data) { // funkce success zpracovává data
                var print = '';
                items = new Array();

                $.each(data, function () {
                    items[this.id] = {
                        name: this.name,
                        type: this.type,
                        description: this.description,
                        price: this.price
                    };
                    print += "<tr><td>" + this.id + "</td><td>" + this.name + "</td><td>" + this.type + "</td><td>" + this.description + "</td><td>" + this.price + "</td><td><button type='button' class='btn btn-default' onclick='updateMachine(" + this.id + ")' >Tractorize (=update)</button><button type='button' class='btn btn-danger' onclick='deleteMachine(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button></td></tr>";
                });
                $('table#users tbody').empty().append(print);


            }

            function getAllMachines() {
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine', // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }

            function createMachine() {

                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine', // ukazujeme URL a
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify({"name": "m1_super", "type": "EXCAVATOR", "description": "Nice machine.", "price": 42}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                    },
                    error: fail
                });
            }
            // tractorize
            function updateMachine(id) {
                items[id].type = "TRACTOR";
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine/update/' + id, // ukazujeme URL a
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(items[id]),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                    },
                    error: fail
                });
            }

            function deleteMachine(id) {

                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine/delete/' + id, // ukazujeme URL a
                    type: 'DELETE',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                    },
                    error: fail
                });
            }
            function getType(search) {
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/machine/type/' + search, // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }

        </script>
    </head>
    <body>

        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <!-- potrebujeme dodat class="active" pokud ma byt odkaz aktivni -->
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="customers.jsp">Customer tests</a></li>
                        <li><a href="machines.jsp">Machines tests</a></li>
                    </ul> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <h1>Machines tests</h1>

        <div class="alert alert-danger" id="alert">
            <a href="#" class="close" onclick="$('#alert').hide();">&times;</a>
            <strong>Error!</strong> There was a problem with your network connection. Or maybe another problem.
        </div>        

        <button onClick="createMachine()">Create machine</button>
        <button onClick="getAllMachines()">Get ALL machines</button>
        <button onclick="getType('TRACTOR')">Get ALL tractors</button>
        <table class="table table-striped" id="users">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Tools</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <script>
            $('#alert').hide();
        </script>

    </body>
</html>
