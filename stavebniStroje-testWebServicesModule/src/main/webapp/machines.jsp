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

            function formatDate(date) {
                var pad = function (val) {
                    var value = String(val);
                    while (value.length < 2)
                        value = '0' + value;
                    return value;
                };
                return date.getFullYear() + '-' + pad(date.getMonth() + 1) + '-' + pad(date.getDate());
            }

            function flushRevisionList(id, data) {
                console.log(data);
                var ul = $('#mRevisions');
                ul.empty();
                ul.append('<button onclick="addRevision(' + id + ', \'' + formatDate(new Date()) + '\')">Add revision</button>');
                $.each(data, function () {
                    var li = $('<li class="list-group-item"></li>');
                    li.html('<span class="badge"><button type="button" onclick="deleteRevision(' + this.id + ', ' + id + ')"><span aria-hidden="true">&times;</span></button></span>');
                    li.append(new Date(this.dateOfRevision).toDateString());
                    ul.append(li);
                });
            }
            function flushMachine(data) {
                $('#mId').text(data.id);
                $('#mName').text(data.name);
                $('#mType').text(data.type);
                $('#mPrice').text(data.price);
                $('#mAvail').text(data.available ? 'Yes' : 'No');
                $('#mDescription').text(data.description);
                flushRevisionList(data.id, data.revisions);
            }

            function getMachine(id) {
                $.ajax({
                    url: '/pa165/rest/service/machine/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        flushMachine(data);
                    },
                    error: fail
                });
            }

            function deleteRevision(id, machineId) {
                $.ajax({
                    url: '/pa165/rest/service/revision/delete/' + id,
                    type: 'DELETE',
                    success: function (data, textStatus) {
                        console.log(data);
                        getMachine(machineId);
                    },
                    error: fail
                });
            }

            function addRevision(id, date) {
                $.ajax({
                    url: '/pa165/rest/service/revision',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                            {
                                machineId: id,
                                dateOfRevision: date
                            }),
                    success: function () {
                        getMachine(id);
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
                    print += "<tr>";
                    print += "<td>" + this.id + "</td>";
                    print += "<td>" + this.name + "</td>"
                    print += "<td>" + this.type + "</td>";
                    print += "<td>" + this.description + "</td>";
                    print += "<td>" + this.price + "</td>";
                    print += "<td>";
                    print += "<button type='button' class='btn btn-default' onclick='getMachine(" + this.id + ")'>Detail</button>";
                    print += "<button type='button' class='btn btn-default' onclick='updateMachine(" + this.id + ")' >Tractorize (=update)</button>";
                    print += "<button type='button' class='btn btn-danger' onclick='deleteMachine(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button>";
                    print += "</td>";
                    print += "</tr>";
                });
                $('table#users tbody').empty().append(print);


            }

            function getAllMachines() {
                $.ajax({
                    url: '/pa165/rest/service/machine', // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }

            function createMachine() {

                $.ajax({
                    url: '/pa165/rest/service/machine', // ukazujeme URL a
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify(
                            {
                                name: "m1_super",
                                type: "EXCAVATOR",
                                description: "Nice machine.",
                                price: 42
                            }),
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
                    url: '/pa165/rest/service/machine/update/' + id, // ukazujeme URL a
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
                    url: '/pa165/rest/service/machine/delete/' + id, // ukazujeme URL a
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
                    url: '/pa165/rest/service/machine/type/' + search, // ukazujeme URL a
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
                        <li><a href="rents.jsp">Rents tests</a></li>
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
        <h3>Detail</h3>
        <div class="row">
            <div class="col-sm-1">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Id</h3>
                    </div>
                    <div class="panel-body" id="mId">
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Name</h3>
                    </div>
                    <div class="panel-body" id="mName">
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Type</h3>
                    </div>
                    <div class="panel-body" id="mType">
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Price</h3>
                    </div>
                    <div class="panel-body" id="mPrice">
                    </div>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Available</h3>
                    </div>
                    <div class="panel-body" id="mAvail">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Description</h3>
                </div>
                <div class="panel-body" id="mDescription">
                </div>
            </div>
        </div>
        <h4>Revisions</h4>

        <ul class="list-group" id="mRevisions">
        </ul>

        <script>
            $('#alert').hide();
            $(function () {
                getAllMachines();
            });
        </script>

    </body>
</html>
