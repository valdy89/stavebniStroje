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
            var currentDate = new Date();

            function fail() {
                $('#alert').show();
            }

            function formatDate(date) {
                date = date || new Date();
                var pad = function (val) {
                    var value = String(val);
                    while (value.length < 2)
                        value = '0' + value;
                    return value;
                };
                return date.getFullYear() + '-' + pad(date.getMonth() + 1) + '-' + pad(date.getDate());
            }

            function prepareEntities() {
                for (var i = 0; i < 3; ++i) {
                    $.ajax({
                        url: '/pa165/rest/service/customer',
                        type: 'POST',
                        contentType: "application/json",
                        data: JSON.stringify({firstName: "tert_" + i, secondName: "test_" + i, address: "test", legalStatus: "NATURAL"}),
                        success: function (data, textStatus) {
                            console.log(data);
                            idTranslator.customer[idTranslator.customer.length] = data.id;
                        },
                        error: fail
                    });
                }
                for (var i = 0; i < 3; ++i) {
                    $.ajax({
                        url: '/pa165/rest/service/machine',
                        type: 'POST',
                        contentType: "application/json",
                        data: JSON.stringify(
                                {
                                    name: "m1_super_" + i,
                                    type: "EXCAVATOR",
                                    description: "The version is " + i + " - what a beautiful machine.",
                                    price: 42 + i
                                }),
                        success: function (data, textStatus) {
                            console.log(data);
                            idTranslator.machine[idTranslator.machine.length] = data.id;
                        },
                        error: fail
                    });
                }
            }

            function buttonCustomer(id, c) {
                return "<button type='button' class='btn btn-default' onclick='changeCustomer(" + id + ", " + c + ")' >C: " + c + "</button>";
            }
            function buttonMachine(id, c) {
                return "<button type='button' class='btn btn-default' onclick='changeMachine(" + id + ", " + c + ")' >M: " + c + "</button>";
            }

            function flushList(data) {
                var print = '';
                items = new Array();

                $.each(data, function () {
                    items[this.id] = {
                        machineId: this.machineId,
                        customerId: this.customerId,
                        from: this.from,
                        to: this.to
                    };
                    print += "<tr>";
                    print += "<td>" + this.id + "</td>";
                    print += "<td>" + this.machineName + "</td>"
                    print += "<td>" + this.customerName + "</td>";
                    print += "<td>" + formatDate(new Date(this.from)) + "</td>";
                    print += "<td>" + formatDate(new Date(this.to)) + "</td>";
                    print += "<td>";
                    for (var j = 0; j < 3; ++j) {
                        print += buttonMachine(this.id, j + 1);
                    }
                    for (var j = 0; j < 3; ++j) {
                        print += buttonCustomer(this.id, j + 1);
                    }
                    print += "<button type='button' class='btn btn-danger' onclick='deleteRent(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button>";
                    print += "</td>";
                    print += "</tr>";
                });
                $('table#rents tbody').empty().append(print);


            }
            function getAllRents() {
                $.ajax({
                    url: '/pa165/rest/service/rent',
                    type: 'GET',
                    success: flushList,
                    error: fail
                });

            }

            function createRent() {

                $.ajax({
                    url: '/pa165/rest/service/rent',
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify(
                            {
                                machineId: 1,
                                customerId: 1,
                                from: formatDate(currentDate),
                                to: formatDate(currentDate)
                            }),
                    success: function (data, textStatus) {
                        console.log(data);
                        getAllRents();
                    },
                    error: fail
                });
                currentDate.setDate(currentDate.getDate() + 1);
            }

            function changeMachine(id, mId) {
                items[id].machineId = mId;
                $.ajax({
                    url: '/pa165/rest/service/rent/' + id,
                    type: 'PUT',
                    contentType: "application/json",
                    data: JSON.stringify(items[id]),
                    success: function (data, textStatus) {
                        console.log(data);
                        getAllRents();
                    },
                    error: fail
                });
            }
            function changeCustomer(id, cId) {
                items[id].customerId = cId;
                $.ajax({
                    url: '/pa165/rest/service/rent/' + id,
                    type: 'PUT',
                    contentType: "application/json",
                    data: JSON.stringify(items[id]),
                    success: function (data, textStatus) {
                        console.log(data);
                        getAllRents();
                    },
                    error: fail
                });
            }

            function deleteRent(id) {

                $.ajax({
                    url: '/pa165/rest/service/rent/' + id,
                    type: 'DELETE',
                    success: function (data, textStatus) {
                        console.log(data);
                        getAllRents()();
                    },
                    error: fail
                });
            }
            function getRentsByDate(date) {
                $.ajax({
                    url: '/pa165/rest/service/rent/date/' + date,
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
        <h3>Rents</h3>

        <div class="alert alert-danger" id="alert">
            <a href="#" class="close" onclick="$('#alert').hide();">&times;</a>
            <strong>Error!</strong> There was a problem with your network connection. Or maybe another problem.
        </div>        
        <button onclick="prepareEntities()">Prepare entities</button>
        <button onClick="createRent()">Create rent</button>
        <button onClick="getAllRents()">Get ALL rents</button>
        <button onclick="getRentsByDate(formatDate())">Get ALL rents over today</button>
        <table class="table table-striped" id="rents">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Machine</th>
                    <th>Customer</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Tools</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <script>
            $('#alert').hide();
            $(function () {
                getAllRents();
            });
        </script>

    </body>
</html>
